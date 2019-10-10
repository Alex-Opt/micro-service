package com.ly.mt.core.data.trade.dao;

import com.ly.mt.core.data.trade.entity.TradeOrders;

import java.util.List;

/**
 * TradeOrders操作接口
 *
 * @author taoye
 */
public interface TradeOrdersDao {
    /**
     * 从redis根据id查询TradeOrders
     * redis不存在则查询mysql
     *
     * @param id 订单ID
     * @return TradeOrders
     * @author taoye
     */
    TradeOrders getTradeOrdersByIdFromRedis(String id);

    /**
     * 从mysql查询List<TradeOrders>
     *
     * @param tradeOrders 查询条件
     * @return List<TradeOrders>
     * @author taoye
     */
    List<TradeOrders> listTradeOrdersFromMysql(TradeOrders tradeOrders);

    /**
     * 更新TradeOrders,id不能为空
     *
     * @param tradeOrders 更新数据
     * @return 更新结果
     * @author taoye
     */
    int updateTradeOrders(TradeOrders tradeOrders);

    /**
     * 自动更新完成状态
     *
     * @return 更新结果
     * @author taoye
     */
    int updateFinishStatus();

    /**
     * 自动更新取消状态
     *
     * @param tradeOrders 更新数据
     * @return 更新结果
     * @author taoye
     */
    int updateCancelStatus(List<TradeOrders> tradeOrders);

    /**
     * 根据时间区间查询自动取消时间在这个区间的未支付订单
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<TradeOrders> getWillCancelOrderByTime(String startTime, String endTime);

    /**
     * 通过订单号查询订单id
     * @param orderNo
     * @return
     */
    String getOrderIdByOrderNo(Long orderNo);
}