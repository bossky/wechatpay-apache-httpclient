package com.wechat.pay.service;

/**
 */
public class WechatApiV3Exception extends WechatApiException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String code;

    protected String message;

    protected WechatApiExceptionDetail detail;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WechatApiExceptionDetail getDetail() {
        return detail;
    }

    public void setDetail(WechatApiExceptionDetail detail) {
        this.detail = detail;
    }
}
