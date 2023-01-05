package com.wechat.pay.service.normal;

import java.util.List;

public class NormalQueryPromoDetail {

	/**
	 * 券ID
	 */
	protected String couponId;
	/**
	 * 优惠名称
	 */
	protected String name;

	/**
	 * 优惠范围
	 * <p>
	 * GLOBAL：全场代金券
	 * <p>
	 * SINGLE：单品优惠
	 */
	protected String scope;

	/**
	 * 优惠类型	CASH：充值型代金券
	 */
	protected String type;


	/**
	 * 优惠券面额
	 */
	protected int amount;
	/**
	 * 活动ID
	 */
	protected String stockId;
	/**
	 * 微信出资，单位为分
	 */
	protected int wechatpayContribute;
	/**
	 * 商户出资，单位为分
	 */
	protected int merchantContribute;
	/**
	 * 其他出资，单位为分
	 */
	protected int otherContribute;
	/**
	 * 优惠币种
	 */
	protected String currency;

	protected List<NormalQueryPromoItem> goodsDetail;

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public int getWechatpayContribute() {
		return wechatpayContribute;
	}

	public void setWechatpayContribute(int wechatpayContribute) {
		this.wechatpayContribute = wechatpayContribute;
	}

	public int getMerchantContribute() {
		return merchantContribute;
	}

	public void setMerchantContribute(int merchantContribute) {
		this.merchantContribute = merchantContribute;
	}

	public int getOtherContribute() {
		return otherContribute;
	}

	public void setOtherContribute(int otherContribute) {
		this.otherContribute = otherContribute;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<NormalQueryPromoItem> getGoodsDetail() {
		return goodsDetail;
	}

	public void setGoodsDetail(List<NormalQueryPromoItem> goodsDetail) {
		this.goodsDetail = goodsDetail;
	}
}
