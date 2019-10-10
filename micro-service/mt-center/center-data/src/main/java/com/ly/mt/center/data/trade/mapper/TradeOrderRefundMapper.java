package com.ly.mt.center.data.trade.mapper;

import com.ly.mt.center.data.trade.entity.TradeOrderRefund;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeOrderRefundMapper {
    /**
     * @Description 保存TradeOrderRefund
     * @Author taoye
     */
    void insertTradeOrderRefund(TradeOrderRefund tradeOrderRefund);

    /**
     * @Description 删除TradeOrderRefund
     * @Author taoye
     */
    void deleteTradeOrderRefund(TradeOrderRefund tradeOrderRefund);

    /**
     * @Description 更新TradeOrderRefund
     * @Author taoye
     */
    int updateTradeOrderRefund(TradeOrderRefund tradeOrderRefund);

    /**
     * @Description 查询TradeOrderRefund
     * @Author taoye
     */
    TradeOrderRefund getTradeOrderRefund(TradeOrderRefund tradeOrderRefund);
}