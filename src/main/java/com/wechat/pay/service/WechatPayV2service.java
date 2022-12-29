package com.wechat.pay.service;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class WechatPayV2service extends WechatPayService {

	protected static final Logger _Logger = LoggerFactory.getLogger(WechatPayV2service.class);

	protected String merchantId;

	protected String apiV2Key;

	protected static XmlMapper MAPPER;

	static {
		MAPPER = new XmlMapper();
		MAPPER.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	public WechatPayV2service() {
		super(10, 10);
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

	@Override
	protected HttpClientBuilder createBuilder() {
		return HttpClientBuilder.create();
	}

	protected String sign(SignType signType, String content) {
		switch (signType) {
		case MD5:
			return SignUtil.md5(content).toUpperCase();
		case HMAC_SHA256:
			return SignUtil.HMACSHA256(content, apiV2Key).toUpperCase();
		default:
			throw new IllegalArgumentException("不支持的签名方式" + signType);
		}
	}

}
