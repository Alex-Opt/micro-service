package com.ly.mt.center.data.trade.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface TradeOrderVieService {
    /**
     * @Description 保存TradeOrderVie
     * @Author taoye
     */
    ResponseJson insertTradeOrderVie(JSONObject jsonObject);

    /**
     * @Description 删除TradeOrderVie
     * @Author taoye
     */
    ResponseJson deleteTradeOrderVie(JSONObject jsonObject);

    /**
     * @Description 更新TradeOrderVie
     * @Author taoye
     */
    ResponseJson updateTradeOrderVie(JSONObject jsonObject);

    /**
     * @Description 查询TradeOrderVie
     * @Author taoye
     */
    ResponseJson getTradeOrderVie(JSONObject jsonObject);
}