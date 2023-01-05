package com.wechat.pay.service.normal;


import java.util.List;

public class NormalQueryResponse {

    /**
     * 应用ID
     */
    protected String appid;
    /**
     * 直连商户
     */
    protected String mchid;
    /**
     * 商户订单号
     * <p>
     * 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯
     */
    protected String outTradeNo;
    /**
     * 微信支付系统生成的订单号
     */
    protected String transactionId;
    /**
     * 交易类型
     * <p>
     * 交易类型，枚举值：
     * JSAPI：公众号支付
     * NATIVE：扫码支付
     * APP：APP支付
     * MICROPAY：付款码支付
     * MWEB：H5支付
     * FACEPAY：刷脸支付
     */
    protected String tradeType;
    /**
     * 交易状态
     * <p>
     * 交易状态，枚举值：
     * SUCCESS：支付成功
     * REFUND：转入退款
     * NOTPAY：未支付
     * CLOSED：已关闭
     * REVOKED：已撤销（仅付款码支付会返回）
     * USERPAYING：用户支付中（仅付款码支付会返回）
     * PAYERROR：支付失败（仅付款码支付会返回）
     */
    protected String tradeState;
    /**
     * 交易状态描述
     */
    protected String tradeStateDesc;

    /**
     * 付款银行
     * <p>
     * 银行类型，采用字符串类型的银行标识
     */
    protected String bankType;
    /**
     * 附加数据
     * <p>
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用，实际情况下只有支付完成状态才会返回该字段。
     */
    protected String attach;
    /**
     * 支付完成时间，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
     */
    protected String successTime;
    /**
     * 支付者
     */
    protected NormalJsApiPayer payer;
    /**
     * 订单金额
     */
    protected NormalQueryAmount amount;
    /**
     * 场景信息
     */
    protected NormalQuerySceneInfo sceneInfo;
    /**
     * 优惠功能
     */
    protected List<NormalQueryPromoDetail> promotionDetail;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public String getTradeStateDesc() {
        return tradeStateDesc;
    }

    public void setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;
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

    public NormalJsApiPayer getPayer() {
        return payer;
    }

    public void setPayer(NormalJsApiPayer payer) {
        this.payer = payer;
    }

    public NormalQueryAmount getAmount() {
        return amount;
    }

    public void setAmount(NormalQueryAmount amount) {
        this.amount = amount;
    }

    public NormalQuerySceneInfo getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(NormalQuerySceneInfo sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

    public List<NormalQueryPromoDetail> getPromotionDetail() {
        return promotionDetail;
    }

    public void setPromotionDetail(List<NormalQueryPromoDetail> promotionDetail) {
        this.promotionDetail = promotionDetail;
    }

}
