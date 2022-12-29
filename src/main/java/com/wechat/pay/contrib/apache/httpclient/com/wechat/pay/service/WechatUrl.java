package com.wechat.pay.contrib.apache.httpclient.com.wechat.pay.service;

import java.util.concurrent.atomic.AtomicBoolean;


public class WechatUrl {

    private static final String MAIN_URL = "api.mch.weixin.qq.com";
    private static final String BACK_URL = "api2.mch.weixin.qq.com";

    private static final AtomicBoolean MAIN = new AtomicBoolean();

    public static String getUrl() {
        if (MAIN.get()) {
            return MAIN_URL;
        } else {
            return BACK_URL;
        }
    }

    public static void switchUrl() {
        synchronized (MAIN) {
            MAIN.set(!MAIN.get());
        }
    }


}
