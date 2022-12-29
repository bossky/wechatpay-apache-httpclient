package com.wechat.pay.contrib.apache.httpclient.com.wechat.pay.service.combine;

import java.util.List;

/**
 * @author daibo
 * @date 2022/12/29 17:03
 */
public class CombineJsApiRequest {

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
    protected List<CombineJsApiSubOrder> subOrders;
    /**
     * 支付者信息
     */
    protected CombineJsApiPayerInfo combinePayerInfo;
    /**
     * 订单生成时间，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒125毫秒。
     */
    protected String timeStart;
    /**
     * 订单失效时间，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
     */
    protected String timeExpire;
    /**
     * 通知地址
     */
    protected String notifyUrl;

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

    public List<CombineJsApiSubOrder> getSubOrders() {
        return subOrders;
    }

    public void setSubOrders(List<CombineJsApiSubOrder> subOrders) {
        this.subOrders = subOrders;
    }

    public CombineJsApiPayerInfo getCombinePayerInfo() {
        return combinePayerInfo;
    }

    public void setCombinePayerInfo(CombineJsApiPayerInfo combinePayerInfo) {
        this.combinePayerInfo = combinePayerInfo;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
