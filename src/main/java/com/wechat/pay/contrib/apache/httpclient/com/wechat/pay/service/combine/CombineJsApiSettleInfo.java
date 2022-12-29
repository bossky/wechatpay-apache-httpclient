package com.wechat.pay.contrib.apache.httpclient.com.wechat.pay.service.combine;

public class CombineJsApiSettleInfo {

    /**
     * 是否指定分账，枚举值
     * true：是
     * false：否
     */
    protected boolean profitSharing;
    /**
     * 补差金额
     * SettleInfo.profit_sharing为true时，该金额才生效。
     * 注意：单笔订单最高补差金额为5000元
     */
    protected long subsidyAmount;

    public boolean isProfitSharing() {
        return profitSharing;
    }

    public void setProfitSharing(boolean profitSharing) {
        this.profitSharing = profitSharing;
    }
}
