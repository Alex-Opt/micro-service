package com.ly.mt.home.tob.pay.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

import java.util.Map;

/**
 * @description: 支付
 * @author: linan
 * @date: 2019/7/17
 */
public interface PayService {
    /**
     * 支付宝WAP支付
     * @param orderNo
     * @param returnUrl
     * @return
     */
    String alPay(String orderNo, String returnUrl, String tradeType);

    /**
     * 微信WAP支付
     * @param orderNo
     * @param tradeType
     * @return
     */
    String wxPay(String orderNo, String tradeType);

    /**
     * jsapi支付
     * @param code
     * @param orderNo
     * @return
     */
    Map jsapiPay(String code, String orderNo);

    /**
     * 订单支付状态
     *
     * @param orderNo
     * @return
     */
    JSONObject status(String orderNo);

}
