package com.wechat.pay.service.partner;

import java.util.List;

public class PartnerJsApiPromoDetail {


	/**
	 * 订单原价
	 */
	protected int costPrice;
	/**
	 * 商家小票ID
	 */
	protected String invoiceId;
	/**
	 * 单品列表
	 */
	protected List<PartnerJsApiPromoItem> goodsDetail;

	public PartnerJsApiPromoDetail() {
	}

	public int getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public List<PartnerJsApiPromoItem> getGoodsDetail() {
		return goodsDetail;
	}

	public void setGoodsDetail(List<PartnerJsApiPromoItem> goodsDetail) {
		this.goodsDetail = goodsDetail;
	}
}
