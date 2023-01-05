package com.wechat.pay.service.bill;

import com.wechat.pay.service.bill.enums.BillHashType;

/**
 * @author daibo
 * @date 2023/1/5 16:55
 */
public class BillFundFlowSubMerchantItem {

    /**
     * 账单文件序号
     * <p>
     * 商户将多个文件按账单文件序号的顺序合并为完整的资金账单文件，起始值为1
     */
    protected int billSequence;
    /**
     * 账单下载地址
     * <p>
     * 供下一步请求账单文件的下载地址，该地址30s内有效。
     */
    protected String downloadUrl;
    /**
     * 加密密钥
     * <p>
     * 加密账单文件使用的加密密钥。密钥用商户证书的公钥进行加密，然后进行Base64编码
     */
    protected String encryptKey;

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
     * 随机字符串
     * <p>
     * 加密账单文件使用的随机字符串
     */
    protected String nonce;

    public int getBillSequence() {
        return billSequence;
    }

    public void setBillSequence(int billSequence) {
        this.billSequence = billSequence;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }

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

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }
}
