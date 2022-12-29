package com.wechat.pay.service.partner;


public class PartnerJsApiRequest {

    /**
     * 服务商应用ID
     */
    protected String spAppid;
    /**
     * 服务商户号
     */
    protected String spMchid;
    /**
     * 子商户应用ID
     */
    protected String subAppid;
    /**
     * 子商户号
     */
    protected String subMchid;
    /**
     * 商品描述
     */
    protected String description;
    /**
     * 商户订单号
     * <p>
     * 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯
     */
    protected String outTradeNo;
    /**
     * 交易结束时间
     * <p>
     * 订单失效时间，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
     */
    protected String timeExpire;
    /**
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用，实际情况下只有支付完成状态才会返回该字段。
     */
    protected String attach;
    /**
     * 通知地址
     * 通知URL必须为直接可访问的URL，不允许携带查询串，要求必须为https地址。
     */
    protected String notifyUrl;
    /**
     * 订单优惠标
     */
    protected String goodsTag;
    /**
     * 电子发票入口开放标识
     * <p>
     * 传入true时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效。
     * true：是
     * false：否
     */
    protected boolean supportFapiao;
    /**
     * 结算信息
     */
    protected PartnerJsApiSettleInfo settleInfo;
    /**
     * 订单金额信息
     */
    protected PartnerJsApiAmount amount;
    /**
     * 支付者信息
     */
    protected PartnerJsApiPayer payer;


    public String getSpAppid() {
        return spAppid;
    }

    public void setSpAppid(String spAppid) {
        this.spAppid = spAppid;
    }

    public String getSpMchid() {
        return spMchid;
    }

    public void setSpMchid(String spMchid) {
        this.spMchid = spMchid;
    }

    public String getSubAppid() {
        return subAppid;
    }

    public void setSubAppid(String subAppid) {
        this.subAppid = subAppid;
    }

    public String getSubMchid() {
        return subMchid;
    }

    public void setSubMchid(String subMchid) {
        this.subMchid = subMchid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public boolean isSupportFapiao() {
        return supportFapiao;
    }

    public void setSupportFapiao(boolean supportFapiao) {
        this.supportFapiao = supportFapiao;
    }

    public PartnerJsApiSettleInfo getSettleInfo() {
        return settleInfo;
    }

    public void setSettleInfo(PartnerJsApiSettleInfo settleInfo) {
        this.settleInfo = settleInfo;
    }

    public PartnerJsApiAmount getAmount() {
        return amount;
    }

    public void setAmount(PartnerJsApiAmount amount) {
        this.amount = amount;
    }

    public PartnerJsApiPayer getPayer() {
        return payer;
    }

    public void setPayer(PartnerJsApiPayer payer) {
        this.payer = payer;
    }
}
