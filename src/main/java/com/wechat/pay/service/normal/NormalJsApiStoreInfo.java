package com.wechat.pay.service.normal;

public class NormalJsApiStoreInfo {

    /**
     * 门店编号
     */
    protected String id;
    /**
     * 门店名称
     */
    protected String name;
    /**
     * 地区编码
     */
    protected String areaCode;
    /**
     * 详细地址
     */
    protected String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
