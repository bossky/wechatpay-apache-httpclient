package com.wechat.pay.service.combine;


public class CombineQueryAmount {

    /**
     * 子单金额，单位为分。
     * 境外场景下，标价金额要超过商户结算币种的最小单位金额，例如结算币种为美元，则标价金额必须大于1美分
     */
    protected long totalAmount;
    /**
     * 标价币种
     * 符合ISO 4217标准的三位字母代码，人民币：CNY。
     */
    protected String currency = "CNY";
    /**
     * 订单现金支付金额。
     */
    protected long payerAmount;
    /**
     * 现金支付币种
     * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY。
     */
    protected String payerCurrency = "CNY";

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getPayerAmount() {
        return payerAmount;
    }

    public void setPayerAmount(long payerAmount) {
        this.payerAmount = payerAmount;
    }

    public String getPayerCurrency() {
        return payerCurrency;
    }

    public void setPayerCurrency(String payerCurrency) {
        this.payerCurrency = payerCurrency;
    }
}
