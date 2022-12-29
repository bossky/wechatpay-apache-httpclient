package com.wechat.pay.contrib.apache.httpclient.com.wechat.pay.service;

/**
 * @author daibo
 * @date 2022/12/29 15:48
 */
public abstract class WechatApiException extends Exception {


    public abstract String getCode();


}
