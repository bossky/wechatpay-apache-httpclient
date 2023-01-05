package com.wechat.pay.service.combine;

public class CombineNativeResponse {

    /**
     * 此URL用于生成支付二维码，然后提供给用户扫码支付
     */
    protected String codeUrl;

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
}
