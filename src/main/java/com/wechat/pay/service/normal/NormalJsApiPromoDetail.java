package com.wechat.pay.service.normal;

import java.util.List;

public class NormalJsApiPromoDetail {


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
	protected List<NormalJsApiPromoItem> goodsDetail;

	public NormalJsApiPromoDetail() {
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

	public List<NormalJsApiPromoItem> getGoodsDetail() {
		return goodsDetail;
	}

	public void setGoodsDetail(List<NormalJsApiPromoItem> goodsDetail) {
		this.goodsDetail = goodsDetail;
	}
}
