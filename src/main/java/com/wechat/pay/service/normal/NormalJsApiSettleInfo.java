package com.wechat.pay.service.normal;

public class NormalJsApiSettleInfo {

    /**
     * 是否指定分账，枚举值
     * true：是
     * false：否
     */
    protected boolean profitSharing;

    public boolean isProfitSharing() {
        return profitSharing;
    }

    public void setProfitSharing(boolean profitSharing) {
        this.profitSharing = profitSharing;
    }
}
