package com.ly.mt.activity.wechart.service;

import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 现金红包
 *
 * @author taoye
 */
public interface WeChartRedService {
    /**
     * 微信现金红包
     *
     * @param openId       微信openid
     * @param securityCode 防伪码
     * @return 红包发放结果
     * @author taoye
     */
    ResponseJson sendRed(String openId, String securityCode);

    /**
     * 微信现金红包发放结果查询
     *
     * @param orderNo 订单号
     * @return 红包发放结果
     * @author taoye
     */
    ResponseJson getRedStatus(String orderNo);
}