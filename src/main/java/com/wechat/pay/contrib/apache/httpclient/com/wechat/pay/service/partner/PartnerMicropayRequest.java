package com.wechat.pay.contrib.apache.httpclient.com.wechat.pay.service.partner;

/**
 * @author daibo
 * @date 2022/12/29 18:34
 */
public class PartnerMicropayRequest {

    /**
     * 服务商的APPID
     */
    protected String appid;
    /**
     * 子商户公众账号ID
     */
    protected String subAppid;
    /**
     * 商户号
     */
    protected String mchId;
    /**
     * 子商户号
     */
    protected String subMchId;
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
     * 商品或支付单简要描述，格式要求：门店品牌名-城市分店名-实际商品名称
     */
    protected String body;
    /**
     * 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     */
    protected String attach;
    /**
     * 商户系统内部订单号，要求32个字符内（最少6个字符），只能是数字、大小写字母_-|*且在同一个商户号下唯一
     */
    protected String outTradeNo;
    /**
     * 订单总金额，单位为分，只能为整数
     */
    protected long totalFee;
    /**
     * 货币类型
     */
    protected String feeType = "CNY";
    /**
     * 终端IP
     */
    protected String spbillCreateIp;
    /**
     * 指定支付方式
     * <p>
     * no_credit--指定不能使用信用卡支付
     */
    protected String limitPay;
    /**
     * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     */
    protected String timeStart;
    /**
     * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010
     */
    protected String timeExpire;
    /**
     * 扫码支付付款码，设备读取用户微信中的条码或者二维码信息
     * （用户付款码规则：18位纯数字，前缀以10、11、12、13、14、15开头）
     */
    protected String authCode;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSubAppid() {
        return subAppid;
    }

    public void setSubAppid(String subAppid) {
        this.subAppid = subAppid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(long totalFee) {
        this.totalFee = totalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
