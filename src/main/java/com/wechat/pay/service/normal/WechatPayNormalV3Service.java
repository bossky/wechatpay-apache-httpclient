package com.wechat.pay.service.normal;

import java.io.IOException;
import java.util.Map;

import com.wechat.pay.contrib.apache.httpclient.exception.ParseException;
import com.wechat.pay.contrib.apache.httpclient.exception.ValidationException;
import com.wechat.pay.service.WechatApiException;
import com.wechat.pay.service.WechatPayV3Service;

/**
 * 微信V3普通商户
 */
public class WechatPayNormalV3Service extends WechatPayV3Service {

    public static String TRANSACTIONS_PATH = "/v3/pay/transactions/";

    public WechatPayNormalV3Service() {
        super();
    }

    public WechatPayNormalV3Service(int connectionSecond, int soSecond) {
        super(connectionSecond, soSecond);
    }

    /**
     * jsapi支付
     *
     * @param request 请求
     * @return 结果
     * @throws IOException        IO异常
     * @throws WechatApiException 微信API异常
     */
    public NormalJsApiResponse jsApi(NormalJsApiRequest request) throws IOException, WechatApiException {
        request.setMchid(merchantId);
        return exe(TRANSACTIONS_PATH + "jsapi", request, NormalJsApiResponse.class);
    }

    /**
     * app支付
     *
     * @param request 请求
     * @return 结果
     * @throws IOException        IO异常
     * @throws WechatApiException 微信API异常
     */
    public NormalJsApiResponse app(NormalJsApiRequest request) throws IOException, WechatApiException {
        request.setMchid(merchantId);
        return exe(TRANSACTIONS_PATH + "app", request, NormalJsApiResponse.class);
    }


    /**
     * h5支付(注意payer_client_ip必传)
     *
     * @param request 请求
     * @return 结果
     * @throws IOException        IO异常
     * @throws WechatApiException 微信API异常
     */
    public NormalH5Response h5(NormalJsApiRequest request) throws IOException, WechatApiException {
        request.setMchid(merchantId);
        return exe(TRANSACTIONS_PATH + "h5", request, NormalH5Response.class);
    }

    /**
     * native支付
     * <p>
     * 商户Native支付下单接口，微信后台系统返回链接参数code_url，商户后台系统将code_url值生成二维码图片，用户使用微信客户端扫码后发起支付。
     *
     * @param request 请求
     * @return 结果
     * @throws IOException        IO异常
     * @throws WechatApiException 微信API异常
     */
    public NormalNativeResponse nativePay(NormalJsApiRequest request) throws IOException, WechatApiException {
        request.setMchid(merchantId);
        return exe(TRANSACTIONS_PATH + "native", request, NormalNativeResponse.class);
    }


    /**
     * 生成调起支付的表单
     *
     * @param appId    应用id
     * @param prepayId 预支付id
     * @return 表单
     */
    public Map<String, Object> genApiForm(String appId, String prepayId) {
        return genApiForm(appId, getMerchantPrivateKey(), prepayId);
    }


    /**
     * 查询
     *
     * @param transactionId 微信支付订单号
     * @return 查询结果
     * @throws IOException        IO异常
     * @throws WechatApiException 微信API异常
     */
    public NormalQueryResponse queryById(String transactionId) throws IOException, WechatApiException {
        return exe(TRANSACTIONS_PATH + "id/" + transactionId + "?mchid=" + merchantId, null, NormalQueryResponse.class);
    }

    /**
     * 查询
     *
     * @param outTradeNo 商户交易号
     * @return 查询结果
     * @throws IOException        IO异常
     * @throws WechatApiException 微信API异常
     */
    public NormalQueryResponse queryByTradeNo(String outTradeNo) throws IOException, WechatApiException {
        return exe(TRANSACTIONS_PATH + "out-trade-no/" + outTradeNo + "?mchid=" + merchantId, null, NormalQueryResponse.class);
    }

    /**
     * 关闭订单
     *
     * @param outTradeNo 商户交易号
     * @throws IOException        IO异常
     * @throws WechatApiException 微信API异常
     */
    public void close(String outTradeNo) throws IOException, WechatApiException {
        NormalCloseRequest request = new NormalCloseRequest();
        request.setMchid(merchantId);
        exe(TRANSACTIONS_PATH + "out-trade-no/" + outTradeNo + "/close", request, null);
    }

    /**
     * 支付通知
     *
     * @param wechatPaySerial HTTP头Wechatpay-Serial
     * @param nonce           HTTP头Wechatpay-Nonce 中的应答随机串。
     * @param timestamp       HTTP头Wechatpay-Timestamp 中的应答时间戳。
     * @param signature       HTTP头Wechatpay-Signature中的应答签名
     * @param body            应答主体（response Body），需要按照接口返回的顺序进行验签，错误的顺序将导致验签失败。
     * @return 回调结果
     * @throws ValidationException 验证错误
     * @throws ParseException      解析错误
     */
    public NormalQueryResponse payNotification(String wechatPaySerial, String nonce, String timestamp, String signature, String body) throws ValidationException, ParseException {
        return parse(wechatPaySerial, nonce, timestamp, signature, body, NormalQueryResponse.class);
    }
}
