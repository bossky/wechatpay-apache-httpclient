package com.wechat.pay.service.normal;

import java.io.IOException;

import com.wechat.pay.service.SignType;
import com.wechat.pay.service.SignUtil;
import com.wechat.pay.service.WechatApiException;
import com.wechat.pay.service.WechatPayV2Service;
import com.wechat.pay.service.partner.PartnerMicropayRequest;
import com.wechat.pay.service.partner.PartnerMicropayResponse;

/**
 * 微信V2普通商户（主要付款码支付还不支付V3）
 */
public class WechatPayV2NormalService extends WechatPayV2Service {

    public static String TRANSACTIONS_PATH = "/pay/";

    public WechatPayV2NormalService() {
        super();
    }

    public WechatPayV2NormalService(int connectionSecond, int soSecond) {
        super(connectionSecond, soSecond);
    }
    /**
     * 付款码支付
     *
     * @param request 请求
     * @return 响应
     */
    public PartnerMicropayResponse micropay(PartnerMicropayRequest request) throws IOException, WechatApiException {
        request.setMchId(merchantId);
        request.setNonceStr(SignUtil.getNonceStr());
        request.setSign(SignUtil.autoSign(request, SignType.MD5, getApiV2Key()));
        return exe(TRANSACTIONS_PATH + "micropay", request, PartnerMicropayResponse.class);
    }


}
