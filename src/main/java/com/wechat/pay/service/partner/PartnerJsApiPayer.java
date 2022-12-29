package com.wechat.pay.service.partner;

public class PartnerJsApiPayer {

	/**
	 * 用户服务标识
	 * <p>
	 * 用户在服务商appid下的唯一标识
	 */
	protected String spOpenid;
	/**
	 * 用户在子商户appid下的唯一标识。若传sub_openid，那sub_appid必填。
	 */
	protected String subOpenid;

	public PartnerJsApiPayer() {
	}

	public PartnerJsApiPayer(String spOpenid) {
		this.spOpenid = spOpenid;

	}

	public PartnerJsApiPayer(String spOpenid, String subOpenid) {
		this.spOpenid = spOpenid;
		this.subOpenid = subOpenid;
	}

	public String getSpOpenid() {
		return spOpenid;
	}

	public void setSpOpenid(String spOpenid) {
		this.spOpenid = spOpenid;
	}

	public String getSubOpenid() {
		return subOpenid;
	}

	public void setSubOpenid(String subOpenid) {
		this.subOpenid = subOpenid;
	}
}
