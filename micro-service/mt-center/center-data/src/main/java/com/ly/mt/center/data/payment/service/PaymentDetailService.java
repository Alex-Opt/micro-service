package com.ly.mt.center.data.payment.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface PaymentDetailService {
    /**
     * @Description 插入PaymentDetail
     * @Author taoye
     */
    ResponseJson insertPaymentDetail(JSONObject jsonObject);
}