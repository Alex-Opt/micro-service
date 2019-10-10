package com.ly.mt.task.gy.timing.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.data.trade.entity.TradeOrders;

import java.util.List;

public interface OrderTimingService {
    /**
     * 推送订单到管易
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject sendTrade(String json) throws Exception;

    /**
     * 查询订单的发货信息的接口
     *
     * @return
     * @throws Exception
     */
    JSONObject getDeliveryInfoByOrderId(String json) throws Exception;

    /**
     * 批量推送订单到管易系统
     *
     * @param orderist
     * @return
     * @throws Exception
     */
    void sendTradeOrdersToGY(List<TradeOrders> orderist) throws Exception;

    /**
     * 查询出推送到管易或者蜂鸟的订单集合
     *
     * @return
     */
    List<TradeOrders> getOrderList();
}
