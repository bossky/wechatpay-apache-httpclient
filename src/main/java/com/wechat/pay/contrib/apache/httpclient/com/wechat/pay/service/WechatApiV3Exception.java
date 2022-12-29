package com.wechat.pay.contrib.apache.httpclient.com.wechat.pay.service;

/**
 * @author daibo
 * @date 2022/12/29 15:48
 */
public class WechatApiV3Exception extends WechatApiException {

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
