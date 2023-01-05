package com.wechat.pay.service.normal;

public class NormalJsApiResponse {

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
