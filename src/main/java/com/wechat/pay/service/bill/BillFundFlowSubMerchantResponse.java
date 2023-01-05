package com.wechat.pay.service.bill;

import java.util.Comparator;
import java.util.List;

/**
 * @author daibo
 * @date 2023/1/5 16:55
 */
public class BillFundFlowSubMerchantResponse {

    /**
     * 下载信息总数
     */
    protected String downloadBillCount;
    /**
     * 下载信息明细
     */
    protected List<BillFundFlowSubMerchantItem> downloadBillList;

    public String getDownloadBillCount() {
        return downloadBillCount;
    }

    public void setDownloadBillCount(String downloadBillCount) {
        this.downloadBillCount = downloadBillCount;
    }

    public List<BillFundFlowSubMerchantItem> getDownloadBillList() {
        return downloadBillList;
    }

    public void setDownloadBillList(List<BillFundFlowSubMerchantItem> downloadBillList) {
        if (null != downloadBillList) {
            downloadBillList.sort(Comparator.comparingInt(BillFundFlowSubMerchantItem::getBillSequence));
        }
        this.downloadBillList = downloadBillList;
    }
}
