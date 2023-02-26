package com.wechat.pay.service;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.security.PrivateKey;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.http.NoHttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public abstract class WechatPayService {

	protected static final Logger _Logger = LoggerFactory.getLogger(WechatPayService.class);
	/**
	 * 默认链接超时
	 */
	public static int DEFAULT_CONNECTION_SECOND = 3;
	/**
	 * 默认读取超时
	 */
	public static int DEFAULT_SO_SECOND = 10;

	/**
	 * 最大链接数
	 */
	public static int DEFAULT_MAX_TOTAL = 20;
	/**
	 * 最大路由链接数，根据连接到的主机对MaxTotal的一个细分
	 */
	public static int DEFAULT_MAX_PER_ROUTE = 20;

	/**
	 * HTTP连接管理
	 */
	protected PoolingHttpClientConnectionManager httpClientManager;
	/**
	 * 请求参数
	 */
	protected RequestConfig httpParams;
	/**
	 * 网络参数
	 */
	protected SocketConfig socketParams;

	protected HttpClientBuilder builder;

	public WechatPayService() {
		this(DEFAULT_CONNECTION_SECOND, DEFAULT_SO_SECOND);
	}

	public WechatPayService(int connectionSecond, int soSecond) {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		// 将最大连接数增加
		cm.setMaxTotal(DEFAULT_MAX_TOTAL);
		// 将每个路由基础的连接增加
		cm.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
		httpClientManager = cm;
		httpParams = RequestConfig.custom().setConnectionRequestTimeout(connectionSecond * 1000)
				.setSocketTimeout(soSecond * 1000).build();
		socketParams = SocketConfig.custom().setSoTimeout(soSecond * 1000).build();
	}

	/**
	 * 获取请求客户端
	 *
	 * @return 请求客户端
	 */
	protected CloseableHttpClient getClient() {
		httpClientManager.closeExpiredConnections();
		httpClientManager.closeIdleConnections(1, TimeUnit.HOURS);
		HttpClientBuilder builder = getBuilder().setConnectionManager(httpClientManager)
				.setDefaultRequestConfig(httpParams).setDefaultSocketConfig(socketParams);
		return builder.build();
	}

	/**
	 * 获取构建者
	 *
	 * @return 构建者
	 */
	protected HttpClientBuilder getBuilder() {
		if (null == builder) {
			builder = createBuilder();
		}
		return builder;
	}

	/**
	 * 创建构建者
	 *
	 * @return 构建者
	 */
	protected HttpClientBuilder createBuilder() {
		return HttpClientBuilder.create();
	}

	/**
	 * 对象转字符串
	 *
	 * @param request 请求类
	 * @return 请求字符串
	 */
	protected abstract String toString(Object request);

	/**
	 * 字符串转对象
	 *
	 * @param result 请求结果
	 * @param clazz  响应类
	 * @param <E>    响应类
	 * @return 响应结果
	 */
	protected abstract <E> E fromString(String result, Class<E> clazz);

	/**
	 * 请求
	 *
	 * @param path       请求路径
	 * @param request    请求
	 * @param clazz      响应类
	 * @param <REQUEST>  请求类
	 * @param <RESPONSE> 响应类
	 * @return 执行结果
	 * @throws IOException        IO异常
	 * @throws WechatApiException 微信异常
	 */
	public <REQUEST, RESPONSE> RESPONSE exe(String path, REQUEST request, Class<RESPONSE> clazz)
			throws IOException, WechatApiException {
		String result = doExe(path, toString(request));
		if (null == clazz) {
			return null;
		}
		return fromString(result, clazz);
	}

	/**
	 * 执行调用
	 *
	 * @param path    请求路径
	 * @param content 请求内容
	 * @return 执行结果
	 * @throws IOException        IO异常
	 * @throws WechatApiException 微信异常
	 */
	protected String doExe(String path, String content) throws IOException, WechatApiException {
		String host = WechatUrl.getUrl();
		String url = host + path;
		IOException last = null;
		for (int i = 0; i < 3; i++) {
			boolean success = false;
			try {
				if (_Logger.isTraceEnabled()) {
					_Logger.trace("请求,{},{}", url, content);
				}
				String result = doExe(URI.create(url), content);
				if (_Logger.isTraceEnabled()) {
					_Logger.trace("返回,{}", result);
				}
				success = true;
				return result;
			} catch (HttpHostConnectException | NoHttpResponseException e) {
				_Logger.warn("{}请求异常", url);
				last = e;
			} catch (SocketTimeoutException | ConnectTimeoutException e) {
				_Logger.warn("{}链接异常", url);
				last = e;
			} catch (IOException e) {
				_Logger.warn("{}IO异常", url);
				last = e;
			} catch (WechatApiException e) {
				_Logger.warn("微信异常,code={}", e.getCode(), e);
				throw e;
			} finally {
				if (!success) {
					WechatUrl.switchUrl();
				}
			}
		}
		throw last;
	}

	/**
	 * 执行调用
	 *
	 * @param uri     链接
	 * @param content 内容
	 * @return 执行结果
	 * @throws IOException        IO异常
	 * @throws WechatApiException 微信异常
	 */
	protected abstract String doExe(URI uri, String content) throws IOException, WechatApiException;

	/**
	 * 生成调起支付的表单
	 *
	 * @param appId    应用id
	 * @param key      密钥
	 * @param prepayId 预支付id
	 * @return 表单
	 */
	public static Map<String, Object> genApiForm(String appId, PrivateKey key, String prepayId) {

//        签名串一共有四行，每一行为一个参数。行尾以\n（换行符，ASCII编码值为0x0A）结束，包括最后一行。
//        如果参数本身以\n结束，也需要附加一个\n
//        应用ID
//        时间戳
//        随机字符串
//        订单详情扩展字符串
		TreeMap<String, Object> form = new TreeMap<>();
		String ts = String.valueOf(SignUtil.getTimestampSecond());
		String nonceStr = SignUtil.getNonceStr();
		String packageStr = "prepay_id=" + prepayId;
		// 服务商申请的公众号或移动应用appid。
		form.put("appId", appId);
		// 当前的时间秒数
		form.put("timeStamp", ts);
		// 随机字符串
		form.put("nonceStr", nonceStr);
		// 订单详情扩展字 统一支付接口返回的符串 prepay_id参数值,提交格式如:prepay_id=***
		form.put("package", packageStr);
		StringBuilder sb = new StringBuilder();
		sb.append(appId).append('\n');
		sb.append(ts).append('\n');
		sb.append(nonceStr).append('\n');
		sb.append(packageStr).append('\n');
		SignType signType = SignType.RSA;
		String sign = SignUtil.rsa(sb.toString(), key);
		form.put("signType", signType.getName());
		form.put("paySign", sign);
		_Logger.info("签名字符串:{}", sb.toString());
		_Logger.info("签名数据:appId={},timeStamp={},nonceStr={},package={},signType={},paySign={}", appId, ts, nonceStr,
				packageStr, signType.getName(), sign);
		return form;
	}

}
