package com.ly.mt.center.data.trade.mapper;

import com.ly.mt.center.data.trade.entity.TradeOrders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeOrdersMapper {
    /**
     * @Description 插入TradeOrders
     * @Author taoye
     */
    void insertTradeOrders(TradeOrders tradeOrders);

    /**
     * @Description 删除TradeOrders
     * @Author taoye
     */
    int  deleteTradeOrders(TradeOrders tradeOrders);

    /**
     * @Description 更新TradeOrders
     * @Author taoye
     */
    int updateTradeOrders(TradeOrders tradeOrders);

    /**
     * @Description 查询TradeOrders
     * @Author taoye
     */
    TradeOrders getTradeOrders(TradeOrders tradeOrders);

    /**
     * @Description 查询TradeOrders集合
     * @Author taoye
     */
    List<TradeOrders> listTradeOrders(TradeOrders tradeOrders);

    /**
     * 根据订单来源，查询 订单数据
     * @param tradeOrders
     * @return
     */
    List<TradeOrders> getTradeOrdersBySource(TradeOrders tradeOrders);

    /**
     * 修改订单金额和支付方式
     * @param
     * @return
     */
    int updateTradeOrdersPaymentType(TradeOrders tradeOrders);


    /**
     * 根据订单编号查询订单数据
     * @param tradeOrders
     * @return
     */
    TradeOrders getTradeOrdersByOrderNo(TradeOrders tradeOrders);

    /**
     * 根据订单来源，查询 订单数据
     * @param tradeOrders
     * @return
     */
    List<TradeOrders> getTradeOrdersByOrderSource(TradeOrders tradeOrders);

}