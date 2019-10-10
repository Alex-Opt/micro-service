package com.ly.mt.center.data.trade.mapper;

import com.ly.mt.center.data.trade.entity.TradeOrderMedia;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeOrderMediaMapper {
    int deleteByPrimaryKey(Long id);

    int insertTradeOrderMedia(TradeOrderMedia record);

    int insertSelective(TradeOrderMedia record);

    TradeOrderMedia selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeOrderMedia record);

    int updateByPrimaryKey(TradeOrderMedia record);
}