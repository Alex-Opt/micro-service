package com.ly.mt.center.data.trade.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface TradeOrderMediaService {
    /**
     * 插入TradeOrderMeida
     * @param jsonObject
     * @return
     */
    ResponseJson insertTradeOrderMeida(JSONObject jsonObject);
}
