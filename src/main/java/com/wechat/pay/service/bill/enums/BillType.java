package com.wechat.pay.service.bill.enums;

/**
 * @author daibo
 * @date 2023/1/5 18:16
 */
public enum BillType {
    /**
     * ：返回当日所有订单信息（不含充值退款订单）
     */
    ALL,
    /***
     * 返回当日成功支付的订单（不含充值退款订单）
     */
    SUCCESS,
    /**
     * ：返回当日退款订单（不含充值退款订单）
     */
    REFUND;
}
