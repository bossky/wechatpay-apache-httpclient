package com.wechat.pay.service.refund;

/**
 * @author daibo
 * @date 2022/12/29 16:38
 */
public class RefundCallBackResponse {

    /**
     * 商户订单号
     */
    protected String outTradeNo;
    /**
     * 微信支付订单号
     */
    protected String transactionId;
    /**
     * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    protected String outRefundNo;

    /**
     * 微信支付退款单号
     */
    protected String refundId;
    /**
     * 退款状态
     * <p>
     * 退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往服务商平台-交易中心，手动处理此笔退款。
     * 枚举值：
     * SUCCESS：退款成功
     * CLOSED：退款关闭
     * PROCESSING：退款处理中
     * ABNORMAL：退款异常
     */
    protected String refund_status;

    /**
     * 退款成功时间，当退款状态为退款成功时有返回。
     */
    protected String successTime;


    /**
     * 退款入账账户
     * <p>
     * 取当前退款单的退款入账方，有以下几种情况：
     * 1）退回银行卡：{银行名称}{卡类型}{卡尾号}
     * 2）退回支付用户零钱:支付用户零钱
     * 3）退还商户:商户基本账户商户结算银行账户
     * 4）退回支付用户零钱通:支付用户零钱通
     */
    protected String userReceivedAccount;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(String refund_status) {
        this.refund_status = refund_status;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(String successTime) {
        this.successTime = successTime;
    }

    public String getUserReceivedAccount() {
        return userReceivedAccount;
    }

    public void setUserReceivedAccount(String userReceivedAccount) {
        this.userReceivedAccount = userReceivedAccount;
    }
}
