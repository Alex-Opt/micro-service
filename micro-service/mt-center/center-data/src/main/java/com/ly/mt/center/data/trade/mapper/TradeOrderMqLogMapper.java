package com.ly.mt.center.data.trade.mapper;

import com.ly.mt.center.data.trade.entity.TradeOrderMqLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeOrderMqLogMapper {
    /**
     * @Description 保存TradeOrderMqLog
     * @Author taoye
     */
    void insertTradeOrderMqLog(TradeOrderMqLog tradeOrderMqLog);

    /**
     * @Description 删除TradeOrderMqLog
     * @Author taoye
     */
    void deleteTradeOrderMqLog(TradeOrderMqLog tradeOrderMqLog);

    /**
     * @Description 更新TradeOrderMqLog
     * @Author taoye
     */
    int updateTradeOrderMqLog(TradeOrderMqLog tradeOrderMqLog);

    /**
     * @Description 查询TradeOrderMqLog
     * @Author taoye
     */
    TradeOrderMqLog getTradeOrderMqLog(TradeOrderMqLog tradeOrderMqLog);
}