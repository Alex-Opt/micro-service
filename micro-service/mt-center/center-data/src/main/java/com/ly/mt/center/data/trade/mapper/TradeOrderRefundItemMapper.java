package com.ly.mt.center.data.trade.mapper;

import com.ly.mt.center.data.trade.entity.TradeOrderRefundItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeOrderRefundItemMapper {
    /**
     * @Description 保存TradeOrderRefundItem
     * @Author taoye
     */
    void insertTradeOrderRefundItem(TradeOrderRefundItem tradeOrderRefundItem);

    /**
     * @Description 删除TradeOrderRefundItem
     * @Author taoye
     */
    void deleteTradeOrderRefundItem(TradeOrderRefundItem tradeOrderRefundItem);

    /**
     * @Description 更新TradeOrderRefundItem
     * @Author taoye
     */
    int updateTradeOrderRefundItem(TradeOrderRefundItem tradeOrderRefundItem);

    /**
     * @Description 查询TradeOrderRefundItem
     * @Author taoye
     */
    TradeOrderRefundItem getTradeOrderRefundItem(TradeOrderRefundItem tradeOrderRefundItem);
}