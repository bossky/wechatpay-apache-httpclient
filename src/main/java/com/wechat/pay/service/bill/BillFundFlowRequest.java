package com.wechat.pay.service.bill;

/**
 *
 */
public class BillFundFlowRequest {

    /**
     * 账单日期	格式yyyy-MM-dd  仅支持三个月内的账单下载申请。
     */
    protected String billDate;
    /**
     * 资金账户类型 不填则默认是BASIC
     * 枚举值：
     * BASIC：基本账户
     * OPERATION：运营账户
     * FEES：手续费账户
     */
    protected BillAccountType accountType;
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

    public BillAccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(BillAccountType accountType) {
        this.accountType = accountType;
    }

    public BillTarType getTarType() {
        return tarType;
    }

    public void setTarType(BillTarType tarType) {
        this.tarType = tarType;
    }
}
