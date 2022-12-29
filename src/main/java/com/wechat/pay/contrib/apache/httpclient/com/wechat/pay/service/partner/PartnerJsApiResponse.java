package com.wechat.pay.contrib.apache.httpclient.com.wechat.pay.service.partner;

public class PartnerJsApiResponse {

    /**
     * 预支付交易会话标识
     */
    protected String prepayId;

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }
}
