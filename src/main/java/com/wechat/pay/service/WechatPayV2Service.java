package com.wechat.pay.service;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class WechatPayV2Service extends WechatPayService {

	protected static final Logger _Logger = LoggerFactory.getLogger(WechatPayV2Service.class);

	protected String merchantId;

	protected String apiV2Key;

	protected static XmlMapper MAPPER;
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	static {
		MAPPER = new XmlMapper();
		MAPPER.setDateFormat(FORMAT);
		MAPPER.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public WechatPayV2Service() {
		super();
	}

	public WechatPayV2Service(int connectionSecond, int soSecond) {
		super(connectionSecond, soSecond);
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getApiV2Key() {
		return apiV2Key;
	}

	public void setApiV2Key(String apiV2Key) {
		this.apiV2Key = apiV2Key;
	}

	@Override
	protected String toString(Object request) {
		if (null == request) {
			return null;
		}
		try {
			return MAPPER.writeValueAsString(request).replaceAll(request.getClass().getSimpleName(), "xml");
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected <E> E fromString(String result, Class<E> clazz) {
		if (null == result) {
			return null;
		}
		try {
			return MAPPER.readValue(result, clazz);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected String doExe(URI uri, String content) throws IOException, WechatApiException {
		HttpPost post = new HttpPost(uri);
		post.setHeader("Content-Type", "text/html; charset=UTF-8");
		post.setEntity(new ByteArrayEntity(content.getBytes(StandardCharsets.UTF_8)));
		try (CloseableHttpResponse response = getClient().execute(post)) {
			StatusLine status = response.getStatusLine();
			if (status.getStatusCode() / 100 != 2) {
				_Logger.warn("HTTP响应异常：" + response.getStatusLine());
			}
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			WechatApiV2Exception exception = fromString(result, WechatApiV2Exception.class);
			if (!"SUCCESS".equals(exception.getCode())) {
				throw exception;
			}
			if (null != exception.getErrCode() || exception.getErrCode().length() > 0) {
				throw exception;
			}
			return result;
		}
	}

	/**
	 * 格式化时间
	 *
	 * @param date 时间
	 * @return 格式化后
	 */
	public static String formatDate(Date date) {
		if (null == date) {
			return null;
		}
		synchronized (FORMAT) {
			return FORMAT.format(date);
		}
	}

	/**
	 * 解析时间
	 *
	 * @param str 字符串
	 * @return 时间
	 */
	public static Date parseDate(String str) {
		if (null == str) {
			return null;
		}
		synchronized (FORMAT) {
			try {
				return FORMAT.parse(str);
			} catch (ParseException e) {
				_Logger.info("格式错误,{}", str, e);
				return null;
			}
		}
	}
}
