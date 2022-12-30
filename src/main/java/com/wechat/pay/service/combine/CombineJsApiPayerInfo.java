package com.wechat.pay.service.combine;

public class CombineJsApiPayerInfo {

	/**
	 * 使用合单appid获取的对应用户openid。是用户在商户appid下的唯一标识。
	 * <p>
	 * openid和subOpenid二选一
	 */
	protected String openid;
	/**
	 * 服务商模式下，使用某个子商户的Appid获取的对应用户Openid，是用户在该子商户Appid下的唯一标识。openid和sub_openid可以选传其中之一，如果选择传sub_openid，则必须传sub_appid。
	 * <p>
	 * openid和subOpenid二选一
	 */
	protected String subOpenid;

	public CombineJsApiPayerInfo() {

	}

	public CombineJsApiPayerInfo(String openid) {
		this.openid = openid;
	}

	public CombineJsApiPayerInfo(String openid, String subOpenid) {
		this.openid = openid;
		this.subOpenid = subOpenid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSubOpenid() {
		return subOpenid;
	}

	public void setSubOpenid(String subOpenid) {
		this.subOpenid = subOpenid;
	}
}
