package com.wechat.pay.service.bill;

import com.wechat.pay.service.bill.enums.BillTarType;
import com.wechat.pay.service.bill.enums.BillType;

/**
 *
 */
public class BillTradeRequest {

    /**
     * 账单日期	格式yyyy-MM-dd  仅支持三个月内的账单下载申请。
     */
    protected String billDate;
    /**
     * 子商户号，普通商户忽略
     * <p>
     * ● 不填则默认返回服务商下的交易或退款数据。
     * ● 如需下载某个子商户下的交易或退款数据，则该字段必填。
     */
    protected String subMchid;
    /**
     * 账单类型 不填则默认是ALL
     * <p>
     * 枚举值：
     * ALL：返回当日所有订单信息（不含充值退款订单）
     * SUCCESS：返回当日成功支付的订单（不含充值退款订单）
     * REFUND：返回当日退款订单（不含充值退款订单）
     */
    protected BillType billType;
    /**
     * 压缩类型 不填则默认是数据流
     * 枚举值：
     * GZIP：返回格式为.gzip的压缩包账单
     */
    protected BillTarType tarType;

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getSubMchid() {
        return subMchid;
    }

    public void setSubMchid(String subMchid) {
        this.subMchid = subMchid;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    public BillTarType getTarType() {
        return tarType;
    }

    public void setTarType(BillTarType tarType) {
        this.tarType = tarType;
    }
}
