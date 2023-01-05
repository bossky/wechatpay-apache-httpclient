package com.wechat.pay.service.combine;


import java.util.List;

public class CombineQuerySubOrder {

    /**
     * 子单发起方商户号，必须与发起方appid有绑定关系。服务商和电商模式下，传服务商商户号。
     */
    protected String mchid;
    /**
     * 交易类型
     * <p>
     * 枚举值：
     * NATIVE：扫码支付
     * JSAPI：公众号支付
     * APP：APP支付
     * MWEB：H5支付
     */
    protected String tradeType;
    /**
     * 交易状态
     * <p>
     * 枚举值：
     * SUCCESS：支付成功
     * REFUND：转入退款
     * NOTPAY：未支付
     * CLOSED：已关闭
     * USERPAYING：用户支付中
     * PAYERROR：支付失败(其他原因，如银行返回失败)
     * ACCEPT：已接收，等待扣款
     */
    protected String tradeState;
    /**
     * 付款银行
     * <p>
     * 银行类型，采用字符串类型的银行标识。
     */
    protected String bankType;

    /**
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
     */
    protected String attach;
    /**
     * 订单支付时间，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss.sss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。
     */
    protected String successTime;
    /**
     * 微信支付订单号
     */
    protected String transactionId;
    /**
     * 二级商户商户号，由微信支付生成并下发。
     * 服务商子商户的商户号，被合单方。直连商户不用传二级商户号。
     */
    protected String outTradeNo;

    /**
     * 二级商户商户号，由微信支付生成并下发。服务商子商户的商户号，被合单方。直连商户不用传二级商户号。
     */
    protected String subMchid;
    /**
     * 服务商模式下，sub_mchid对应的sub_appid
     */
    protected String subAppid;
    /**
     * 服务商模式下，sub_appid 对应的 openid
     */
    protected String subOpenid;

    /**
     * 订单金额
     */
    protected CombineQueryAmount amount;
    /**
     * 优惠功能
     */
    protected List<CombineQueryPromoDetail> promotionDetail;


    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(String successTime) {
        this.successTime = successTime;
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

    public String getSubMchid() {
        return subMchid;
    }

    public void setSubMchid(String subMchid) {
        this.subMchid = subMchid;
    }

    public String getSubAppid() {
        return subAppid;
    }

    public void setSubAppid(String subAppid) {
        this.subAppid = subAppid;
    }

    public String getSubOpenid() {
        return subOpenid;
    }

    public void setSubOpenid(String subOpenid) {
        this.subOpenid = subOpenid;
    }

    public CombineQueryAmount getAmount() {
        return amount;
    }

    public void setAmount(CombineQueryAmount amount) {
        this.amount = amount;
    }

    public List<CombineQueryPromoDetail> getPromotionDetail() {
        return promotionDetail;
    }

    public void setPromotionDetail(List<CombineQueryPromoDetail> promotionDetail) {
        this.promotionDetail = promotionDetail;
    }
}
