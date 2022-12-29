package com.wechat.pay.contrib.apache.httpclient.com.wechat.pay.service;


import java.util.Objects;

/**
 * 签名方式
 *
 * @author daibo
 */
public enum SignType {
	/**
	 * HMAC-SHA256
	 */
	HMAC_SHA256("HMAC-SHA256"),
	/**
	 * MD5
	 */
	MD5("MD5");
	/**
	 * 名称
	 */
	private final String m_Name;

	/**
	 * 构造
	 *
	 * @param name 名称
	 */
	SignType(String name) {
		m_Name = name;
	}

	/**
	 * 获取名称
	 */
	public String getName() {
		return m_Name;
	}

	/**
	 * 查找签名类型
	 *
	 * @param type 类型
	 * @return 值
	 */
	public static SignType find(String type) {
		if (null == type || 0 == type.length()) {
			return MD5;
		}
		for (SignType t : values()) {
			if (Objects.equals(type, t.getName())) {
				return t;
			}
		}
		throw new IllegalArgumentException("未知的" + type);
	}
}
