package com.ly.mt.order.server.trade.mapper;


import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.trade.TradeOrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单明细表持久层操作
 * @author zhanglifeng
 * @date 2019-05-27
 */
@Mapper
public interface TradeOrderItemServiceMapper {
    int insert(TradeOrderItem record);

    TradeOrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKey(TradeOrderItem record);

    int batchInsert(@Param("list") List<TradeOrderItem> list);

    List<TradeOrderItem> selectByOrderIdAndSkuId(JSONObject findJson);

    List<TradeOrderItem> selectByOrderId(Long orderId);
}