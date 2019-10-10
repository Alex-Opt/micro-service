package com.ly.mt.center.third.al.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @Description 阿里支付接口
 * @Author taoye
 */
public interface AlPayService {
    /**
     * @Description 支付状态查询
     * @Author taoye
     */
    ResponseJson status(JSONObject jsonObject);

    /**
     * @Description 支付回调
     * @Author taoye
     */
    ResponseJson notify(JSONObject jsonObject);

    /**
     * @Description WAP支付
     * @Author taoye
     */
    ResponseJson wapPay(JSONObject jsonObject);

    /**
     * app支付
     *
     * @param jsonObject
     * @return
     */
    ResponseJson appPay(JSONObject jsonObject);

    ResponseJson refund(JSONObject parameter);
}