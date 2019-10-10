package com.ly.mt.blue.tooth.user.service;

import com.ly.mt.core.base.entity.ResponseJson;

public interface UserRepairService {
    /**
     * 填写申请表
     */
    ResponseJson applicationFrom(String name,String mobile,String shippingAddress);


    /**
     * 填写物流公司信息
     */
    ResponseJson applicationLogistics(String repairsId,String logisticsCompany,String logisticsNumber);


    /**
     * 关闭一键换新
     */
    ResponseJson closeBluetoothRepairs(String repairsId);

    /**
     * 确认收货
     */
    ResponseJson confirmReceipt(String repairsId);
}
