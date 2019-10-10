package com.ly.mt.center.data.trade.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface TradeOrderMqLogService {
    /**
     * @Description 保存TradeOrderMqLog
     * @Author taoye
     */
    ResponseJson insertTradeOrderMqLog(JSONObject jsonObject);

    /**
     * @Description 删除TradeOrderMqLog
     * @Author taoye
     */
    ResponseJson deleteTradeOrderMqLog(JSONObject jsonObject);

    /**
     * @Description 更新TradeOrderMqLog
     * @Author taoye
     */
    ResponseJson updateTradeOrderMqLog(JSONObject jsonObject);

    /**
     * @Description 查询TradeOrderMqLog
     * @Author taoye
     */
    ResponseJson getTradeOrderMqLog(JSONObject jsonObject);
}