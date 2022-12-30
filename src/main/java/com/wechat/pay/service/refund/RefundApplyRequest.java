package com.wechat.pay.service.refund;

/**
 * @author daibo
 * @date 2022/12/29 16:34
 */
public class RefundApplyRequest {

	/**
	 * 子商户号 服务商模式必传
	 */
	protected String subMchid;
	/**
	 * 商户订单号
	 * <p>
	 * 商户订单号和微信支付订单号二选一
	 */
	protected String outTradeNo;
	/**
	 * 微信支付订单号
	 * <p>
	 * 商户订单号和微信支付订单号二选一
	 */
	protected String transactionId;
	/**
	 * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
	 */
	protected String outRefundNo;
	/**
	 * 退款原因
	 */
	protected String reason;
	/**
	 * 退款结果回调url
	 */
	protected String notifyUrl;
	/**
	 * 金额信息
	 */
	protected RefundApplyAmount amount;

	public String getSubMchid() {
		return subMchid;
	}

	public void setSubMchid(String subMchid) {
		this.subMchid = subMchid;
	}

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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public RefundApplyAmount getAmount() {
		return amount;
	}

	public void setAmount(RefundApplyAmount amount) {
		this.amount = amount;
	}
}
