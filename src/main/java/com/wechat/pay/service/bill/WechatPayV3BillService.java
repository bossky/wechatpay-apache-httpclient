package com.wechat.pay.service.bill;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
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
        if (null != request.getAccountType() && request.getAccountType().length() > 0) {
            sb.append("&account_type=").append(request.getAccountType());
        }
        if (null != request.getTarType() && request.getTarType().length() > 0) {
            sb.append("&tar_type=").append(request.getTarType());
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
        if (null != request.getAccountType() && request.getAccountType().length() > 0) {
            sb.append("&account_type=").append(request.getAccountType());
        }
        if (null != request.getAlgorithm() && request.getAlgorithm().length() > 0) {
            sb.append("&algorithm=").append(request.getAccountType());
        }
        if (null != request.getTarType() && request.getTarType().length() > 0) {
            sb.append("&tar_type=").append(request.getTarType());
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
