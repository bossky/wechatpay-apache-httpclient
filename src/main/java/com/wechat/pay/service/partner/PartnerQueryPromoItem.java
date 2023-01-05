package com.wechat.pay.service.partner;

public class PartnerQueryPromoItem {


	/**
	 * 商品编码
	 */
	protected String goodsId;

	/**
	 * 商品数量
	 */
	protected int quantity;
	/**
	 * 商品单价
	 */
	protected int unitPrice;
	/**
	 * 商品优惠金额
	 */
	protected int discountAmount;
	/**
	 * 商品备注
	 */
	protected String goodsRemark;

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
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

	public int getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(int discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getGoodsRemark() {
		return goodsRemark;
	}

	public void setGoodsRemark(String goodsRemark) {
		this.goodsRemark = goodsRemark;
	}
}
