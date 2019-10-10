package com.ly.mt.center.data.trade.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface TradeInvoiceService {
    /**
     * @Description 保存TradeInvoice
     * @Author taoye
     */
    ResponseJson insertTradeInvoice(JSONObject jsonObject);

    /**
     * @Description 删除TradeInvoice
     * @Author taoye
     */
    ResponseJson deleteTradeInvoice(JSONObject jsonObject);

    /**
     * @Description 更新TradeInvoice
     * @Author taoye
     */
    ResponseJson updateTradeInvoice(JSONObject jsonObject);

    /**
     * @Description 查询TradeInvoice
     * @Author taoye
     */
    ResponseJson getTradeInvoice(JSONObject jsonObject);
}