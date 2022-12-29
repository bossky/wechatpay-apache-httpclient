package com.wechat.pay.contrib.apache.httpclient.com.wechat.pay.service.partner;


public class PartnerCloseRequest {


    /**
     * 服务商户号
     */
    protected String spMchid;
    /**
     * 子商户号
     */
    protected String subMchid;
    /**
     * 商户订单号
     */
    protected String outTradeNo;

    public String getSpMchid() {
        return spMchid;
    }

    public void setSpMchid(String spMchid) {
        this.spMchid = spMchid;
    }

    public String getSubMchid() {
        return subMchid;
    }

    public void setSubMchid(String subMchid) {
        this.subMchid = subMchid;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
