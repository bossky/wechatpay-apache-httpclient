package com.wechat.pay.service.combine;


public class CombineQueryPayerInfo {

    /**
     * 使用合单appid获取的对应用户openid。是用户在商户appid下的唯一标识。
     */
    protected String openid;


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

}
