package com.wechat.pay.service.partner;

import java.io.IOException;

import com.wechat.pay.service.SignType;
import com.wechat.pay.service.SignUtil;
import com.wechat.pay.service.WechatApiException;
import com.wechat.pay.service.WechatPayV2service;

/**
 *
 */
public class WechatPayV2PartnerService extends WechatPayV2service {

    public static String TRANSACTIONS_PATH = "/pay/";

    /**
     * 付款码支付
     *
     * @param request 请求
     * @return 响应
     */
    public PartnerMicropayResponse micropay(PartnerMicropayRequest request) throws IOException, WechatApiException {
        request.setMchId(merchantId);
        request.setSign(SignUtil.autoSign(request, SignType.MD5, apiV2Key));
        return exe(TRANSACTIONS_PATH + "micropay", request, PartnerMicropayResponse.class);
    }
}
