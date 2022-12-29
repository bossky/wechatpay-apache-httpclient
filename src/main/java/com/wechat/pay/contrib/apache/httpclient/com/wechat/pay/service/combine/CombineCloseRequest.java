package com.wechat.pay.contrib.apache.httpclient.com.wechat.pay.service.combine;

import java.util.List;

/**
 * @author daibo
 * @date 2022/12/29 17:03
 */
public class CombineCloseRequest {

    /**
     * 合单发起方的appid
     */
    protected String combineAppid;

    /**
     * 合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
     */
    protected String combineOutTradeNo;
    /**
     * 子单信息
     */
    protected List<CombineCloseSubOrder> subOrders;

    public String getCombineAppid() {
        return combineAppid;
    }

    public void setCombineAppid(String combineAppid) {
        this.combineAppid = combineAppid;
    }

    public String getCombineOutTradeNo() {
        return combineOutTradeNo;
    }

    public void setCombineOutTradeNo(String combineOutTradeNo) {
        this.combineOutTradeNo = combineOutTradeNo;
    }

    public List<CombineCloseSubOrder> getSubOrders() {
        return subOrders;
    }

    public void setSubOrders(List<CombineCloseSubOrder> subOrders) {
        this.subOrders = subOrders;
    }
}
