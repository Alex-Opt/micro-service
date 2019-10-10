package com.ly.mt.core.data.trade.dao;

import com.ly.mt.core.data.trade.entity.TradeOrderItems;

import java.util.List;

/**
 * TradeOrderItems操作接口
 *
 * @author taoye
 */
public interface TradeOrderItemsDao {
    /**
     * 从redis根据orderId查询List<TradeOrderItems>
     * redis不存在则查询mysql
     *
     * @param orderId 订单ID
     * @return List<TradeOrderItems>
     * @author taoye
     */
    List<TradeOrderItems> listTradeOrderItemsByOrderIdFromRedis(String orderId);
}