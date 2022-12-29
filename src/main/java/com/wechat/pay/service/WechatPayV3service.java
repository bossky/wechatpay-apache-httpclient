package com.wechat.pay.service;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;

import org.apache.http.HttpHeaders;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.exception.HttpCodeException;
import com.wechat.pay.contrib.apache.httpclient.exception.NotFoundException;
import com.wechat.pay.contrib.apache.httpclient.exception.ParseException;
import com.wechat.pay.contrib.apache.httpclient.exception.ValidationException;
import com.wechat.pay.contrib.apache.httpclient.notification.Notification;
import com.wechat.pay.contrib.apache.httpclient.notification.NotificationHandler;
import com.wechat.pay.contrib.apache.httpclient.notification.NotificationRequest;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;

public class WechatPayV3service extends WechatPayService {

    protected static final Logger _Logger = LoggerFactory.getLogger(WechatPayV3service.class);

    protected String merchantId;
    protected String merchantSerialNumber;

    protected String merchantPrivateKey;
    protected String apiV3Key;
    protected NotificationHandler handler;
    protected static ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
        MAPPER.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public WechatPayV3service() {
        super(10, 10);
    }


    public String getMerchantId() {
		return merchantId;
	}


	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}


	public String getMerchantSerialNumber() {
		return merchantSerialNumber;
	}


	public void setMerchantSerialNumber(String merchantSerialNumber) {
		this.merchantSerialNumber = merchantSerialNumber;
	}


	public String getMerchantPrivateKey() {
		return merchantPrivateKey;
	}


	public void setMerchantPrivateKey(String merchantPrivateKey) {
		this.merchantPrivateKey = merchantPrivateKey;
	}


	public String getApiV3Key() {
		return apiV3Key;
	}


	public void setApiV3Key(String apiV3Key) {
		this.apiV3Key = apiV3Key;
	}


	protected String toString(Object request) {
        if (null == request) {
            return null;
        }
        try {
            return MAPPER.writeValueAsString(request);
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
        HttpUriRequest request;
        if (null == content || content.length() == 0) {
            request = new HttpGet(uri);
        } else {
            HttpPost post = new HttpPost(uri);
            post.setEntity(new StringEntity(content, "UTF-8"));
            post.addHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
            request = post;
        }
        request.addHeader(HttpHeaders.ACCEPT, "application/json");
        try (CloseableHttpResponse response = getClient().execute(request)) {
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() / 100 != 2) {
                throw fromString(EntityUtils.toString(response.getEntity()), WechatApiV3Exception.class);
            }
            return EntityUtils.toString(response.getEntity());
        }
    }

    @Override
    protected HttpClientBuilder createBuilder() {
        PrivateKey privateKey = PemUtil.loadPrivateKey(merchantPrivateKey);
        //获取证书管理器实例
        CertificatesManager certificatesManager = CertificatesManager.getInstance();
        // 向证书管理器增加需要自动更新平台证书的商户信息
        try {
            certificatesManager.putMerchant(merchantId, new WechatPay2Credentials(merchantId, new PrivateKeySigner(merchantSerialNumber, privateKey)), apiV3Key.getBytes(StandardCharsets.UTF_8));
        } catch (IOException | GeneralSecurityException | HttpCodeException e) {
            throw new RuntimeException(e);
        }
        // 从证书管理器中获取verifier
        Verifier verifier;
        try {
            verifier = certificatesManager.getVerifier(merchantId);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        handler = new NotificationHandler(verifier, apiV3Key.getBytes(StandardCharsets.UTF_8));
        return WechatPayHttpClientBuilder.create().withMerchant(merchantId, merchantSerialNumber, privateKey).withValidator(new WechatPay2Validator(verifier));
    }

    /**
     * 解析回调
     *
     * @param wechatPaySerial HTTP头Wechatpay-Serial
     * @param nonce           HTTP头Wechatpay-Nonce 中的应答随机串。
     * @param timestamp       HTTP头Wechatpay-Timestamp 中的应答时间戳。
     * @param signature       HTTP头Wechatpay-Signature中的应答签名
     * @param body            应答主体（response Body），需要按照接口返回的顺序进行验签，错误的顺序将导致验签失败。
     * @param <E>             结果类型
     * @return 回调结果
     * @throws ValidationException 验证错误
     * @throws ParseException      解析错误
     */
    public <E> E parse(String wechatPaySerial, String nonce, String timestamp, String signature, String body, Class<E> clazz) throws ValidationException, ParseException {
        Notification notification = parse(wechatPaySerial, nonce, timestamp, signature, body);
        return fromString(notification.getDecryptData(), clazz);

    }

    /**
     * 解析回调
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
    public Notification parse(String wechatPaySerial, String nonce, String timestamp, String signature, String body) throws ValidationException, ParseException {
        getClient();//触发初始化
        NotificationRequest request = new NotificationRequest.Builder().withSerialNumber(wechatPaySerial).withNonce(nonce).withTimestamp(timestamp).withSignature(signature).withBody(body).build();
        // 验签和解析请求体
        return handler.parse(request);
    }
}
