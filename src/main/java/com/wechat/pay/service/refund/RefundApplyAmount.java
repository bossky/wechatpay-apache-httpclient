package com.wechat.pay.service.refund;

/**
 * @author daibo
 * @date 2022/12/29 16:36
 */
public class RefundApplyAmount {

    /**
     * 退款金额
     */
    protected long refund;
    /**
     * 原订单金额
     */
    protected long total;
    /**
     * 符合ISO 4217标准的三位字母代码，目前只支持人民币：CNY。
     */
    protected String currency = "CNY";

    public long getRefund() {
        return refund;
    }

    public void setRefund(long refund) {
        this.refund = refund;
    }

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
