package com.wechat.pay.service.combine;


public class CombineJsApiResponse {

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
