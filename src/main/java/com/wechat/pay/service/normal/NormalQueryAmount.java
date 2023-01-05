package com.wechat.pay.service.normal;

public class NormalQueryAmount {

	/**
	 * 订单总金额，单位为分。
	 */
	protected long total;
	/**
	 * 用户支付金额
	 */
	protected String payerTotal;
	/**
	 * 货币类型 CNY：人民币，境内商户号仅支持人民币。
	 */
	protected String currency;
	/**
	 * 用户支付币种
	 */
	protected String payerCurrency;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getPayerTotal() {
		return payerTotal;
	}

	public void setPayerTotal(String payerTotal) {
		this.payerTotal = payerTotal;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPayerCurrency() {
		return payerCurrency;
	}

	public void setPayerCurrency(String payerCurrency) {
		this.payerCurrency = payerCurrency;
	}
}
