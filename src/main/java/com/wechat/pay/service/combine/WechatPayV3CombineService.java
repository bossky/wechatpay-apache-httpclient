package com.wechat.pay.service.combine;

import java.io.IOException;
import java.util.Map;

import com.wechat.pay.contrib.apache.httpclient.exception.ParseException;
import com.wechat.pay.contrib.apache.httpclient.exception.ValidationException;
import com.wechat.pay.service.WechatApiException;
import com.wechat.pay.service.WechatPayV3Service;

public class WechatPayV3CombineService extends WechatPayV3Service {

	public static String TRANSACTIONS_PATH = "/v3/combine-transactions/";

	public WechatPayV3CombineService() {
		super();
	}

	public WechatPayV3CombineService(int connectionSecond, int soSecond) {
		super(connectionSecond, soSecond);
	}

	/**
	 * jsapi支付
	 *
	 * @param request 请求
	 * @return 结果
	 * @throws IOException        IO异常
	 * @throws WechatApiException 微信API异常
	 */
	public CombineJsApiResponse jsApi(CombineJsApiRequest request) throws IOException, WechatApiException {
		request.setCombineMchid(merchantId);
		request.getSubOrders().forEach(e -> {
			if (null != e.getSubMchid() || e.getSubMchid().length() > 0) {
				e.setMchid(merchantId);
			}
		});
		return exe(TRANSACTIONS_PATH + "jsapi", request, CombineJsApiResponse.class);
	}

	/**
	 * 生成JSAPI调起支付的表单
	 *
	 * @param appId    应用id
	 * @param prepayId 预支付id
	 * @return 表单
	 */
	public Map<String, Object> genJsApiForm(String appId, String prepayId) {
		return genJsApiForm(appId, getMerchantPrivateKey(), prepayId);
	}

	/**
	 * 查询
	 *
	 * @param combineOutTradeNo 合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
	 * @return 查询结果
	 * @throws IOException        IO异常
	 * @throws WechatApiException 微信API异常
	 */
	public CombineQueryResponse query(String combineOutTradeNo) throws IOException, WechatApiException {
		return exe(TRANSACTIONS_PATH + "out-trade-no/" + combineOutTradeNo, null, CombineQueryResponse.class);
	}

	/**
	 * 关闭订单
	 *
	 * @param request 请求
	 * @throws IOException        IO异常
	 * @throws WechatApiException 微信API异常
	 */
	public void close(CombineCloseRequest request) throws IOException, WechatApiException {
		exe(TRANSACTIONS_PATH + "out-trade-no/" + request.getCombineOutTradeNo() + "/close", request, null);
	}

	/**
	 * 支付通知
	 *
	 * @param wechatPaySerial HTTP头Wechatpay-Serial
	 * @param nonce           HTTP头Wechatpay-Nonce 中的应答随机串。
	 * @param timestamp       HTTP头Wechatpay-Timestamp 中的应答时间戳。
	 * @param signature       HTTP头Wechatpay-Signature中的应答签名
	 * @param body            应答主体（response Body），需要按照接口返回的顺序进行验签，错误的顺序将导致验签失败。
	 * @return 回调结果
	 * @throws ValidationException 验证错误
	 * @throws ParseException      解析错误
	 */
	public CombineQueryResponse payNotification(String wechatPaySerial, String nonce, String timestamp,
			String signature, String body) throws ValidationException, ParseException {
		return parse(wechatPaySerial, nonce, timestamp, signature, body, CombineQueryResponse.class);
	}
}
