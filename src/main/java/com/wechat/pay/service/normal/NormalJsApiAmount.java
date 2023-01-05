package com.wechat.pay.service.normal;

public class NormalJsApiAmount {

	/**
	 * 订单总金额，单位为分。
	 */
	protected long total;
	/**
	 * 货币类型 CNY：人民币，境内商户号仅支持人民币。
	 */
	protected String currency = "CNY";

	public NormalJsApiAmount() {

	}

	public NormalJsApiAmount(long total) {
		this.total = total;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
