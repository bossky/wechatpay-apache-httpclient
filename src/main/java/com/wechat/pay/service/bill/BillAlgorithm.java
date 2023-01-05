package com.wechat.pay.service.bill;

/**
 * @author daibo
 * @date 2023/1/5 17:36
 */
public enum BillAlgorithm {
    /**
     * AEAD_AES_256_GCM加密算法
     */
    AEAD_AES_256_GCM,
    /**
     * SM4_GCM加密算法，密钥长度128bit
     */
    SM4_GCM
}
