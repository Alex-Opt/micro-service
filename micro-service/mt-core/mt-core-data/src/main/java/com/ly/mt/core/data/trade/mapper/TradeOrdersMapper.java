package com.ly.mt.core.data.trade.mapper;

import com.ly.mt.core.data.trade.entity.TradeOrders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TradeOrders操作接口
 *
 * @author taoye
 */
@Mapper
public interface TradeOrdersMapper {
    /**
     * 查询TradeOrders
     *
     * @param tradeOrders 查询条件
     * @return TradeOrders
     * @author taoye
     */
    TradeOrders getTradeOrders(TradeOrders tradeOrders);

    /**
     * 查询List<TradeOrders>
     *
     * @param tradeOrders 查询条件
     * @return List<TradeOrders>
     * @author taoye
     */
    List<TradeOrders> listTradeOrders(TradeOrders tradeOrders);

    /**
     * 更新TradeOrders
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
    List<TradeOrders> getWillCancelOrderByTime(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 根据订单号查询订单id
     * @param orderNo
     * @return
     */
    String getOrderIdByOrderNo(Long orderNo);
}