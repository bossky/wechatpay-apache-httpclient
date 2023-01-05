package com.wechat.pay.service.bill;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.util.RsaCryptoUtil;
import com.wechat.pay.service.WechatApiException;
import com.wechat.pay.service.WechatPayV3Service;

/**
 * 帐单服务
 */
public class WechatPayV3BillService extends WechatPayV3Service {

    public static String BILL_PATH = "/v3/bill/";

    /**
     * 默认链接超时
     */
    public static int DEFAULT_DOWNLOAD_CONNECTION_SECOND = 3;
    /**
     * 默认读取超时
     */
    public static int DEFAULT_DOWNLOAD_SO_SECOND = 10 * 60;

    protected PoolingHttpClientConnectionManager httpDownloadClientManager;
    /**
     * 请求参数
     */
    protected RequestConfig httpDownloadParams;
    /**
     * 网络参数
     */
    protected SocketConfig socketDownloadParams;

    protected HttpClientBuilder downloadBuilder;

    public WechatPayV3BillService() {
        this(DEFAULT_CONNECTION_SECOND, DEFAULT_SO_SECOND, DEFAULT_DOWNLOAD_CONNECTION_SECOND, DEFAULT_DOWNLOAD_SO_SECOND);
    }

    public WechatPayV3BillService(int connectionSecond, int soSecond, int downloadConnectionSecond, int soDownloadSecond) {
        super(connectionSecond, soSecond);
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // 将最大连接数增加
        cm.setMaxTotal(DEFAULT_MAX_TOTAL);
        // 将每个路由基础的连接增加
        cm.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
        httpDownloadClientManager = cm;
        httpDownloadParams = RequestConfig.custom().setConnectionRequestTimeout(downloadConnectionSecond * 1000)
                .setSocketTimeout(soSecond * 1000).build();
        socketDownloadParams = SocketConfig.custom().setSoTimeout(soDownloadSecond * 1000).build();
    }

    /**
     * 申请交易帐单
     *
     * @param request 请求
     * @return 响应
     * @throws IOException        IO异常
     * @throws WechatApiException 微信API异常
     */
    public BillTradeResponse tradeBill(BillTradeRequest request) throws IOException, WechatApiException {
        StringBuilder sb = new StringBuilder();
        sb.append("?bill_date=").append(request.getBillDate());
        if (null != request.getSubMchid() && request.getSubMchid().length() > 0) {
            sb.append("&sub_mchid=").append(request.getSubMchid());
        }
        if (null != request.getBillType() && request.getBillType().length() > 0) {
            sb.append("&bill_type=").append(request.getBillType());
        }
        if (null != request.getTarType() && request.getTarType().length() > 0) {
            sb.append("&tar_type=").append(request.getTarType());
        }
        return exe(BILL_PATH + "tradebill" + sb, null, BillTradeResponse.class);
    }

    /**
     * 申请资金账单
     *
     * @param request 请求
     * @return 响应
     * @throws IOException        IO异常
     * @throws WechatApiException 微信API异常
     */
    public BillFundFlowResponse fundFlowBill(BillFundFlowRequest request) throws IOException, WechatApiException {
        StringBuilder sb = new StringBuilder();
        sb.append("?bill_date=").append(request.getBillDate());
        if (null != request.getAccountType()) {
            sb.append("&account_type=").append(request.getAccountType().name());
        }
        if (null != request.getTarType()) {
            sb.append("&tar_type=").append(request.getTarType().name());
        }
        return exe(BILL_PATH + "fundflowbill" + sb, null, BillFundFlowResponse.class);
    }

    /**
     * 申请单个子商户资金账单
     *
     * @param request 请求
     * @return 响应
     * @throws IOException        IO异常
     * @throws WechatApiException 微信API异常
     */
    public BillFundFlowSubMerchantResponse subMerchantFundFlowBill(BillFundFlowSubMerchantRequest request) throws IOException, WechatApiException {
        StringBuilder sb = new StringBuilder();
        sb.append("?bill_date=").append(request.getBillDate());
        sb.append("?sub_mchid=").append(request.getSubMchid());
        if (null != request.getAccountType()) {
            sb.append("&account_type=").append(request.getAccountType().name());
        }
        if (null != request.getAlgorithm()) {
            sb.append("&algorithm=").append(request.getAccountType().name());
        }
        if (null != request.getTarType()) {
            sb.append("&tar_type=").append(request.getTarType().name());
        }
        return exe(BILL_PATH + "sub-merchant-fundflowbill" + sb, null, BillFundFlowSubMerchantResponse.class);
    }


    /**
     * 下载
     *
     * @param url      链接
     * @param consumer 处理函数
     * @throws IOException IO异常
     */
    public void download(String url, Consumer<InputStream> consumer) throws IOException {
        try (CloseableHttpResponse response = getDownloadClient().execute(new HttpGet(URI.create(url)))) {
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() / 100 != 2) {
                throw new IOException("状态码异常:" + status);
            }
            consumer.accept(response.getEntity().getContent());
        }
    }

    /**
     * AEAD_AES_256_GCM 解析
     *
     * @param encryptKey 加密账单文件使用的加密密钥。密钥用商户证书的公钥进行加密，然后进行Base64编码
     * @param nonce      加密账单文件使用的随机字符串
     * @param ciphertext 账单文件密文
     * @return 解密后内容
     */
    public byte[] encryptAeadAes256Gcm(String encryptKey, String nonce, byte[] ciphertext) {
//        步骤1 下载账单文件，得到账单文件密文ciphertext；
//        步骤2 使用商户证书私钥解密从接口获取的加密密钥（变量名：encrypt_key）得到密钥明文key；
//        步骤3 利用步骤一、二中得到的账单密文ciphertext，密钥key和接口返回的随机字符串nonce解密账单，得到账单明文。
        try {
            String aesKey = RsaCryptoUtil.decryptOAEP(encryptKey, getPrivateKeyObject());
            byte[] associatedData = aesKey.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec key = new SecretKeySpec(aesKey.getBytes(StandardCharsets.UTF_8), "AES");
            GCMParameterSpec spec = new GCMParameterSpec(128, nonce.getBytes(StandardCharsets.UTF_8));
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            cipher.updateAAD(associatedData);
            return cipher.doFinal(ciphertext);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new IllegalStateException(e);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    protected CloseableHttpClient getDownloadClient() {
        httpDownloadClientManager.closeExpiredConnections();
        httpDownloadClientManager.closeIdleConnections(1, TimeUnit.HOURS);
        HttpClientBuilder builder = getDownloadBuilder().setConnectionManager(httpClientManager)
                .setDefaultRequestConfig(httpDownloadParams).setDefaultSocketConfig(socketDownloadParams);
        return builder.build();
    }

    protected HttpClientBuilder getDownloadBuilder() {
        if (null == downloadBuilder) {
            downloadBuilder = createDownloadBuilder();
        }
        return downloadBuilder;
    }

    protected HttpClientBuilder createDownloadBuilder() {
        return WechatPayHttpClientBuilder.create().withMerchant(getMerchantId(), getMerchantSerialNumber(), getPrivateKeyObject()).withValidator(response -> true);
    }

}
