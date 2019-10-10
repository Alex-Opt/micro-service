package com.ly.mt.center.data.trade.mapper;

import com.ly.mt.center.data.trade.entity.TradeOrderVie;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeOrderVieMapper {
    /**
     * @Description 保存TradeOrderVie
     * @Author taoye
     */
    void insertTradeOrderVie(TradeOrderVie tradeOrderVie);

    /**
     * @Description 删除TradeOrderVie
     * @Author taoye
     */
    void deleteTradeOrderVie(TradeOrderVie tradeOrderVie);

    /**
     * @Description 更新TradeOrderVie
     * @Author taoye
     */
    int updateTradeOrderVie(TradeOrderVie tradeOrderVie);

    /**
     * @Description 查询TradeOrderVie
     * @Author taoye
     */
    TradeOrderVie getTradeOrderVie(TradeOrderVie tradeOrderVie);
}