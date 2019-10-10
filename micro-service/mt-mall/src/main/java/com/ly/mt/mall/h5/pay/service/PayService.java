package com.ly.mt.mall.h5.pay.service;

import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @Description 支付
 * @Author taoye
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
    ResponseJson wxPay(String orderNo, String spuName, String trade_type);

    /**
     * @Description 微信WAP支付状态
     * @Author taoye
     */
    ResponseJson wxPayStatus(String orderNo);

    /**
     * 微信授权登陆接口
     *
     * @param code
     * @return
     */
    ResponseJson confirmAuthorization(String code);

    /**
     * 微信小程序后者微信浏览器内支付在调用统一下单接口后的再次签名接口
     *
     * @param prePayId
     * @param payType  支付类型：小程序，app，jsapi支付
     * @return
     */
    ResponseJson paySignAgain(String prePayId, String payType);

}
