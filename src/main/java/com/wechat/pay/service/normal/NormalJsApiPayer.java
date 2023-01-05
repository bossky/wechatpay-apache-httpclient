package com.wechat.pay.service.normal;

public class NormalJsApiPayer {


	/**
	 * 用户在直连商户appid下的唯一标识
	 */
	protected String openid;

	public NormalJsApiPayer() {
	}

	public NormalJsApiPayer(String openid) {
		this.openid = openid;

	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
}
