package com.ly.mt.center.data.trade.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface TradeOrderRefundItemService {
    /**
     * @Description 保存TradeOrderRefundItem
     * @Author taoye
     */
    ResponseJson insertTradeOrderRefundItem(JSONObject jsonObject);

    /**
     * @Description 删除TradeOrderRefundItem
     * @Author taoye
     */
    ResponseJson deleteTradeOrderRefundItem(JSONObject jsonObject);

    /**
     * @Description 更新TradeOrderRefundItem
     * @Author taoye
     */
    ResponseJson updateTradeOrderRefundItem(JSONObject jsonObject);

    /**
     * @Description 查询TradeOrderRefundItem
     * @Author taoye
     */
    ResponseJson getTradeOrderRefundItem(JSONObject jsonObject);
}