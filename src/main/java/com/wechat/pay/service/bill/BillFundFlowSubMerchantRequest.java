package com.wechat.pay.service.bill;

import com.wechat.pay.service.bill.enums.BillAccountType;
import com.wechat.pay.service.bill.enums.BillAlgorithm;
import com.wechat.pay.service.bill.enums.BillTarType;

/**
 * @author daibo
 * @date 2023/1/5 16:55
 */
public class BillFundFlowSubMerchantRequest {

    /**
     * 子商户
     */
    protected String subMchid;
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
     * 加密算法
     * <p>
     * 枚举值：
     * AEAD_AES_256_GCM：AEAD_AES_256_GCM加密算法
     * SM4_GCM：SM4_GCM加密算法，密钥长度128bit
     */
    protected BillAlgorithm algorithm;
    /**
     * 压缩类型 不填则默认是数据流
     * 枚举值：
     * GZIP：返回格式为.gzip的压缩包账单
     */
    protected BillTarType tarType;

    public String getSubMchid() {
        return subMchid;
    }

    public void setSubMchid(String subMchid) {
        this.subMchid = subMchid;
    }

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

    public BillAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(BillAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public BillTarType getTarType() {
        return tarType;
    }

    public void setTarType(BillTarType tarType) {
        this.tarType = tarType;
    }
}
