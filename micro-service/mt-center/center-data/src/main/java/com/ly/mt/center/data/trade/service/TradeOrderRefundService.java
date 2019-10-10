package com.ly.mt.center.data.trade.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface TradeOrderRefundService {
    /**
     * @Description 保存TradeOrderRefund
     * @Author taoye
     */
    ResponseJson insertTradeOrderRefund(JSONObject jsonObject);

    /**
     * @Description 删除TradeOrderRefund
     * @Author taoye
     */
    ResponseJson deleteTradeOrderRefund(JSONObject jsonObject);

    /**
     * @Description 更新TradeOrderRefund
     * @Author taoye
     */
    ResponseJson updateTradeOrderRefund(JSONObject jsonObject);

    /**
     * @Description 查询TradeOrderRefund
     * @Author taoye
     */
    ResponseJson getTradeOrderRefund(JSONObject jsonObject);
}