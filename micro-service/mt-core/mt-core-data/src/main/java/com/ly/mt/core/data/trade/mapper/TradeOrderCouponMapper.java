package com.ly.mt.core.data.trade.mapper;

import com.ly.mt.core.data.trade.entity.TradeOrderCoupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TradeOrderCoupon操作接口
 *
 * @author taoye
 */
@Mapper
public interface TradeOrderCouponMapper {
    /**
     * 查询List<TradeOrderCoupon>
     *
     * @param tradeOrderCoupon 查询条件
     * @return List<TradeOrderCoupon>
     * @author taoye
     */
    List<TradeOrderCoupon> listTradeOrderCoupon(TradeOrderCoupon tradeOrderCoupon);
}