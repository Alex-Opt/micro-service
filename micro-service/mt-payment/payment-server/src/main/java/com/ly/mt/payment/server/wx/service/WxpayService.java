package com.ly.mt.payment.server.wx.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description 微信h5支付
 * @Author taoye
 */
public interface WxpayService {
    /**
     * @Description 支付状态
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

    /**
     * WAP申请退款
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject refund(String json) throws  Exception;
}