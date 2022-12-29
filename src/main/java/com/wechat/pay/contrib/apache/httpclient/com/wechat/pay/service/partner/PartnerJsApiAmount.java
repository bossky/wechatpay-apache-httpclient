package com.wechat.pay.contrib.apache.httpclient.com.wechat.pay.service.partner;


public class PartnerJsApiAmount {

    /**
     * 订单总金额，单位为分。
     */
    protected long total;
    /**
     * 货币类型
     * CNY：人民币，境内商户号仅支持人民币。
     */
    protected String currency = "CNY";

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
