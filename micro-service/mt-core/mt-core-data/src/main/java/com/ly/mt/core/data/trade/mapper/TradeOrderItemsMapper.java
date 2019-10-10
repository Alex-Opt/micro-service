package com.ly.mt.core.data.trade.mapper;

import com.ly.mt.core.data.trade.entity.TradeOrderItems;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TradeOrderItems操作接口
 *
 * @author taoye
 */
@Mapper
public interface TradeOrderItemsMapper {
    /**
     * 查询List<TradeOrderItems>
     *
     * @param tradeOrderItems 查询条件
     * @return List<TradeOrderItems>
     * @author taoye
     */
    List<TradeOrderItems> listTradeOrderItems(TradeOrderItems tradeOrderItems);
}