package com.ly.mt.payment.server.alipay.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description 阿里支付接口
 * @Author taoye
 */
public interface AlipayService {
    /**
     * @Description 支付状态查询
     * @Author taoye
     */
    JSONObject status(String json)throws Exception;

    /**
     * @Description 支付回调
     * @Author taoye
     */
    JSONObject notify(String json) throws Exception;

    /**
     * @Description WAP支付
     * @Author taoye
     */
    JSONObject wapPay(String json) throws Exception;
}