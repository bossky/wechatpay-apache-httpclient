package com.wechat.pay.service.bill;

import com.wechat.pay.service.bill.enums.BillHashType;

/**
 *
 */
public class BillFundFlowResponse {

    /**
     * 哈希类型
     * <p>
     * 枚举值：
     * SHA1：SHA1值
     */
    protected BillHashType hashType;
    /**
     * 哈希值
     * <p>
     * 原始账单（gzip需要解压缩）的摘要值，用于校验文件的完整性。
     */
    protected String hashValue;
    /**
     * 账单下载地址
     * <p>
     * 供下一步请求账单文件的下载地址，该地址30s内有效。
     */
    protected String downloadUrl;

    public BillHashType getHashType() {
        return hashType;
    }

    public void setHashType(BillHashType hashType) {
        this.hashType = hashType;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
