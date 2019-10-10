package com.ly.mt.center.data.trade.mapper;

import com.ly.mt.center.data.trade.entity.TradeOrderItems;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TradeOrderItemsMapper {
    /**
     * @Description 保存TradeOrderItems
     * @Author taoye
     */
    void insertTradeOrderItems(TradeOrderItems tradeOrderItems);

    /**
     * @Description 删除TradeOrderItems
     * @Author taoye
     */
    void deleteTradeOrderItems(TradeOrderItems tradeOrderItems);

    /**
     * @Description 更新TradeOrderItems
     * @Author taoye
     */
    int updateTradeOrderItems(TradeOrderItems tradeOrderItems);

    /**
     * @Description 查询TradeOrderItems
     * @Author taoye
     */
    TradeOrderItems getTradeOrderItems(TradeOrderItems tradeOrderItems);

    /**
     * @Description 根据订单id查询
     * @Author zhanglifeng
     * @param orderId
     * @return
     */
    List<TradeOrderItems> listOrderItemsByOrderId(Long orderId);

    /**
     * 根据userId和skuId查询小燕的购买数量
     * @param userId
     */
    int getFreeLittleSmokeCount(@Param("userId") Long userId,@Param("skuId") Long skuId);

    int getmogozeropriceCount(@Param("userId") Long userId,@Param("skuId") Long skuId);
    int gettaozhuangzeropriceCount(@Param("userId") Long userId,@Param("skuId") Long skuId);
}