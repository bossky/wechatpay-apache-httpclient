package com.wechat.pay.contrib.apache.httpclient.com.wechat.pay.service.combine;

import java.util.List;

public class CombineQueryResponse {

    /**
     * 合单发起方的appid
     */
    protected String combineAppid;
    /**
     * 合单发起方商户号，服务商和电商模式下，传服务商商户号。
     */
    protected String combineMchid;
    /**
     * 合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
     */
    protected String combineOutTradeNo;
    /**
     * 子单信息
     */
    protected List<CombineQuerySubOrder> subOrders;
    /**
     * 支付者信息
     */
    protected CombineQueryPayerInfo combinePayerInfo;

    public String getCombineAppid() {
        return combineAppid;
    }

    public void setCombineAppid(String combineAppid) {
        this.combineAppid = combineAppid;
    }

    public String getCombineMchid() {
        return combineMchid;
    }

    public void setCombineMchid(String combineMchid) {
        this.combineMchid = combineMchid;
    }

    public String getCombineOutTradeNo() {
        return combineOutTradeNo;
    }

    public void setCombineOutTradeNo(String combineOutTradeNo) {
        this.combineOutTradeNo = combineOutTradeNo;
    }

    public List<CombineQuerySubOrder> getSubOrders() {
        return subOrders;
    }

    public void setSubOrders(List<CombineQuerySubOrder> subOrders) {
        this.subOrders = subOrders;
    }

    public CombineQueryPayerInfo getCombinePayerInfo() {
        return combinePayerInfo;
    }

    public void setCombinePayerInfo(CombineQueryPayerInfo combinePayerInfo) {
        this.combinePayerInfo = combinePayerInfo;
    }
}
