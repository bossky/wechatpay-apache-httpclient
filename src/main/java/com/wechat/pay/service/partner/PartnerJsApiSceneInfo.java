package com.wechat.pay.service.partner;

public class PartnerJsApiSceneInfo {

    /**
     * 用户终端IP
     */
    protected String payerClientIp;
    /**
     * 商户端设备号
     */
    protected String deviceId;
    /**
     * 商户门店信息
     */
    protected PartnerJsApiStoreInfo storeInfo;

    public String getPayerClientIp() {
        return payerClientIp;
    }

    public void setPayerClientIp(String payerClientIp) {
        this.payerClientIp = payerClientIp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public PartnerJsApiStoreInfo getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(PartnerJsApiStoreInfo storeInfo) {
        this.storeInfo = storeInfo;
    }
}
