package com.ly.mt.center.data.trade.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface TradeOrderCouponService {
    /**
     * @Description 保存TradeOrderCoupon
     * @Author taoye
     */
    ResponseJson insertTradeOrderCoupon(JSONObject jsonObject);

    /**
     * @Description 删除TradeOrderCoupon
     * @Author taoye
     */
    ResponseJson deleteTradeOrderCoupon(JSONObject jsonObject);

    /**
     * @Description 更新TradeOrderCoupon
     * @Author taoye
     */
    ResponseJson updateTradeOrderCoupon(JSONObject jsonObject);

    /**
     * @Description 查询TradeOrderCoupon
     * @Author taoye
     */
    ResponseJson getTradeOrderCoupon(JSONObject jsonObject);
}