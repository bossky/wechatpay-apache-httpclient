package com.wechat.pay.service.combine;

public class CombineJsApiSceneInfo {

    /**
     * 用户终端IP
     */
    protected String payerClientIp;
    /**
     * 商户端设备号
     */
    protected String deviceId;


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


}
