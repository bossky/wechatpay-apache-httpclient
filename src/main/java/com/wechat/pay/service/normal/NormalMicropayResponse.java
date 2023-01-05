package com.wechat.pay.service.normal;

/**
 * @author daibo
 * @date 2022/12/29 18:34
 */
public class NormalMicropayResponse {

    /**
     * 服务商的APPID
     */
    protected String appid;
    /**
     * 商户号
     */
    protected String mchId;
    /**
     * 设备号
     */
    protected String deviceInfo;
    /**
     * 随机字符串
     */
    protected String nonceStr;

    /**
     * 签名
     */
    protected String sign;
    /**
     * 用户在商户appid 下的唯一标识
     */
    protected String openid;
    /**
     * 用户是否关注公众账号，仅在公众账号类型支付有效，取值范围：Y或N;Y-关注;N-未关注
     */
    protected String isSubscribe;
    /**
     * 银行类型，采用字符串类型的银行标识
     */
    protected String bankType;
    /**
     * 货币类型
     */
    protected String feeType = "CNY";
    /**
     * 订单总金额，单位为分，只能为整数
     */
    protected long totalFee;

    /**
     * 微信支付订单号
     */
    protected String transactionId;
    /**
     * 商户系统内部订单号，要求32个字符内（最少6个字符），只能是数字、大小写字母_-|*且在同一个商户号下唯一
     */
    protected String outTradeNo;
    /**
     * 商家数据包，原样返回
     */
    protected String attach;

    /**
     * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     */
    protected String timeEnd;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(long totalFee) {
        this.totalFee = totalFee;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
