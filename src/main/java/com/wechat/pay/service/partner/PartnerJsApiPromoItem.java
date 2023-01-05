package com.wechat.pay.service.partner;

public class PartnerJsApiPromoItem {


	/**
	 * 商户侧商品编码
	 */
	protected String merchantGoodsId;

	/**
	 * 微信支付商品编码
	 */
	protected String wechatpayGoodsId;
	/***
	 * 商品名称
	 */
	protected String goodsName;
	/**
	 * 商品数量
	 */
	protected int quantity;
	/**
	 * 商品单价
	 */
	protected int unitPrice;

	public PartnerJsApiPromoItem() {
	}

	public String getMerchantGoodsId() {
		return merchantGoodsId;
	}

	public void setMerchantGoodsId(String merchantGoodsId) {
		this.merchantGoodsId = merchantGoodsId;
	}

	public String getWechatpayGoodsId() {
		return wechatpayGoodsId;
	}

	public void setWechatpayGoodsId(String wechatpayGoodsId) {
		this.wechatpayGoodsId = wechatpayGoodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
}
