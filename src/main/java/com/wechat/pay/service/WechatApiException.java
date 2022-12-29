package com.wechat.pay.service;

/**
 */
public abstract class WechatApiException extends Exception {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract String getCode();


}
