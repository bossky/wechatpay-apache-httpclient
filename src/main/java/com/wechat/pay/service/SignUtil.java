package com.wechat.pay.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;


/**
 *
 */
public class SignUtil {

	/**
	 * 通过get和is验证
	 *
	 * @param value      对象值
	 * @param signType   签名方式
	 * @param partnerKey 私钥
	 * @return 签名结果
	 */
	public static String autoSign(Object value, SignType signType, String partnerKey) {
		TreeMap<String, Object> map = new TreeMap<>();
		for (Method method : value.getClass().getMethods()) {
			String key;
			String name = method.getName();
			if (name.equals("getClass")) {
				continue;
			}
			if (name.startsWith("get")) {
				key = toKey(name, 3);
			} else if (name.startsWith("is")) {
				key = toKey(name, 2);
			} else {
				key = null;
			}
			if (null == key) {
				continue;
			}
			Object v;
			try {
				v = method.invoke(value);
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
			if (null == v) {
				continue;
			}
			map.put(key, v);

		}
		return sign(map, signType, partnerKey);
	}

	private static String toKey(String name, int start) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Character.toLowerCase(name.charAt(start)));
		for (int i = start + 1; i < name.length(); i++) {
			char ch = name.charAt(i);
			if (Character.isUpperCase(ch)) {
				stringBuilder.append('_');
				stringBuilder.append(Character.toLowerCase(ch));
			} else {
				stringBuilder.append(ch);
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * 签名
	 *
	 * @param map        值集合
	 * @param signType   签名方式
	 * @param partnerKey 私钥
	 * @return 签名结果
	 */
	public static String sign(TreeMap<String, Object> map, SignType signType, String partnerKey) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (null == value) {
				continue;
			}
			stringBuilder.append(key).append('=').append(value).append('&');
		}
		stringBuilder.append("key=").append(partnerKey);
		return sign(stringBuilder.toString(), signType, partnerKey);
	}

	/**
	 * 签名
	 *
	 * @param signType   签名方式
	 * @param partnerKey 私钥
	 * @return 签名结果
	 */
	public static String sign(String content, SignType signType, String partnerKey) {
		switch (signType) {
		case MD5:
			return SignUtil.md5(content).toUpperCase();
		case HMAC_SHA256:
			return SignUtil.HMACSHA256(content, partnerKey).toUpperCase();
		default:
			throw new IllegalArgumentException("不支持的签名方式" + signType);
		}
	}

	public static String md5(String content) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");// 生成一个MD5加密计算摘要
			md.update(content.getBytes());// 计算md5函数

			return new BigInteger(1, md.digest()).toString(16);// 16是表示转换为16进制数
		} catch (Throwable e) {
			throw new RuntimeException("签名异常", e);
		}
	}

	public static String HMACSHA256(String content, String partnerKey) {
		try {
			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(partnerKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
			sha256_HMAC.init(secret_key);
			byte[] array = sha256_HMAC.doFinal(content.getBytes(StandardCharsets.UTF_8));
			return new String(Hex.encodeHex(array));
		} catch (Throwable e) {
			throw new RuntimeException("签名异常", e);
		}
	}

	public static String rsa(String content, PrivateKey partnerKey) {
		try {
			byte[] data = content.getBytes();
			Signature signature = Signature.getInstance("SHA256withRSA");// 这个根据需求填充SHA1WithRSA或SHA256WithRSA
			signature.initSign(partnerKey);
			signature.update(data);
			return Base64.encodeBase64String(signature.sign());
		} catch (Throwable e) {
			throw new RuntimeException("签名异常", e);
		}
	}

	/**
	 * 获取随机字符串
	 * 
	 * @return 随机字符串
	 */
	public static String getNonceStr() {
		return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
	}

	/**
	 * 获取当前秒数
	 *
	 * @return 当前秒数
	 */
	public static long getTimestampSecond() {
		return System.currentTimeMillis() / 1000;
	}
}
