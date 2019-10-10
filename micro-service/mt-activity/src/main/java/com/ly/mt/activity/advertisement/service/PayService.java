package com.ly.mt.activity.advertisement.service;

import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @author wanglong
 */
public interface PayService {
    /**
     * @Description 支付宝WAP支付
     * @Author taoye
     */
    ResponseJson alPay(String orderNo, String returnUrl, String spuName);

    /**
     * @Description 支付宝WAP支付状态
     * @Author taoye
     */
    ResponseJson alPayStatus(String orderNo);

    /**
     * @Description 微信WAP支付
     * @Author taoye
     */
    ResponseJson wxPay(String orderNo, String spuName, String ip,String trade_type);

    /**
     * @Description 微信WAP支付状态
     * @Author taoye
     */
    ResponseJson wxPayStatus(String orderNo);

    /**
     *  微信授权登陆接口
     * @param code
     * @return
     */
    ResponseJson confirmAuthorization(String code);

    /**
     * 微信预付款参数封装
     * @param prePayId
     * @return
     */
    ResponseJson getJsApiParam(String prePayId);
}
