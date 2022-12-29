package com.wechat.pay.service.refund;


import java.io.IOException;

import com.wechat.pay.contrib.apache.httpclient.exception.ParseException;
import com.wechat.pay.contrib.apache.httpclient.exception.ValidationException;
import com.wechat.pay.service.WechatApiException;
import com.wechat.pay.service.WechatPayV3service;

public class WechatPayV3RefundService extends WechatPayV3service {

    public static String REFUND_PATH = "/v3/refund/domestic/";

    /**
     * 申请退款
     *
     * @param request 请求
     * @return 结果
     * @throws IOException        IO异常
     * @throws WechatApiException 微信API异常
     */
    public RefundApplyResponse refund(RefundApplyRequest request) throws IOException, WechatApiException {
        return exe(REFUND_PATH + "refunds", request, RefundApplyResponse.class);
    }

    /**
     * 查询
     *
     * @param subMchid   子商户号
     * @param outTradeNo 商户交易号
     * @return 查询结果
     * @throws IOException        IO异常
     * @throws WechatApiException 微信API异常
     */
    public RefundQueryResponse query(String subMchid, String outTradeNo) throws IOException, WechatApiException {
        return exe(REFUND_PATH + "refunds/" + outTradeNo + "?sub_mchid=" + subMchid, null, RefundQueryResponse.class);
    }
    /**
     * 退款结果通知
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
    public RefundCallBackResponse refundNotification(String wechatPaySerial, String nonce, String timestamp, String signature, String body) throws ValidationException, ParseException {
        return parse(wechatPaySerial, nonce, timestamp, signature, body, RefundCallBackResponse.class);
    }

}
