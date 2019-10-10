package com.ly.mt.center.data.trade.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import springfox.documentation.spring.web.json.Json;

public interface TradeOrdersService {
    /**
     * @Description 插入TradeOrders
     * @Author taoye
     */
    ResponseJson insertTradeOrders(JSONObject jsonObject);

    /**
     * @Description 删除TradeOrders
     * @Author taoye
     */
    ResponseJson deleteTradeOrders(JSONObject jsonObject);

    /**
     * @Description 更新TradeOrders
     * @Author taoye
     */
    ResponseJson updateTradeOrders(JSONObject jsonObject);

    /**
     * @Description 查询TradeOrders
     * @Author taoye
     */
    ResponseJson getTradeOrders(JSONObject jsonObject);

    /**
     * @Description 查询TradeOrders集合
     * @Author taoye
     */
    ResponseJson listTradeOrders(JSONObject jsonObject);

    /**
     * 根据来源，查询订单数据
     * @param jsonObject
     * @return
     */
    ResponseJson getTradeOrdersBySource(JSONObject jsonObject);

    /**
     * 修改订单金额和支付方式
     * @param jsonObject
     * @return
     */
    ResponseJson updateTradeOrdersPaymentType(JSONObject jsonObject);

    /**
     * 根据订单编号查询订单数据
     * @param jsonObject
     * @return
     */
    ResponseJson getTradeOrdersByOrderNo(JSONObject jsonObject);

    /**
     * 根据buyerId和orderSource 查询订单数据
     * @param jsonObject
     * @return
     */
    ResponseJson getTradeOrdersByOrderSource(JSONObject jsonObject);
}