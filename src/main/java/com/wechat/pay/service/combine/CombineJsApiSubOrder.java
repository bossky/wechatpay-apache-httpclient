package com.wechat.pay.service.combine;


public class CombineJsApiSubOrder {

    /**
     * 子单发起方商户号，必须与发起方appid有绑定关系。服务商和电商模式下，传服务商商户号。
     */
    protected String mchid;
    /**
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
     */
    protected String attach;
    /**
     * 订单金额
     */
    protected CombineJsApiAmount amount;
    /**
     * 子单商户订单号
     */
    protected String outTradeNo;
    /**
     * 二级商户商户号，由微信支付生成并下发。服务商子商户的商户号，被合单方。直连商户不用传二级商户号。
     */
    protected String subMchid;
    /**
     * 商品描述
     */
    protected String description;
    /**
     * 结算信息
     */
    protected CombineJsApiSettleInfo settleInfo;
    /**
     * 子商户申请的应用ID，全局唯一。请求基础下单接口时请注意APPID的应用属性，例如公众号场景下，需使用应用属性为公众号的APPID 若sub_openid有传的情况下，sub_appid必填，且sub_appid需与sub_openid对应
     */
    protected String subAppid;

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public CombineJsApiAmount getAmount() {
        return amount;
    }

    public void setAmount(CombineJsApiAmount amount) {
        this.amount = amount;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CombineJsApiSettleInfo getSettleInfo() {
        return settleInfo;
    }

    public void setSettleInfo(CombineJsApiSettleInfo settleInfo) {
        this.settleInfo = settleInfo;
    }

    public String getSubAppid() {
        return subAppid;
    }

    public void setSubAppid(String subAppid) {
        this.subAppid = subAppid;
    }
}
