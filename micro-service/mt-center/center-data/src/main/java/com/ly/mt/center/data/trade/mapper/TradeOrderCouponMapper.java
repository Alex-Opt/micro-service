package com.ly.mt.center.data.trade.mapper;

import com.ly.mt.center.data.trade.entity.TradeOrderCoupon;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeOrderCouponMapper {
    /**
     * @Description 保存TradeOrderCoupon
     * @Author taoye
     */
    void insertTradeOrderCoupon(TradeOrderCoupon tradeOrderCoupon);

    /**
     * @Description 删除TradeOrderCoupon
     * @Author taoye
     */
    void deleteTradeOrderCoupon(TradeOrderCoupon tradeOrderCoupon);

    /**
     * @Description 更新TradeOrderCoupon
     * @Author taoye
     */
    int updateTradeOrderCoupon(TradeOrderCoupon tradeOrderCoupon);

    /**
     * @Description 查询TradeOrderCoupon
     * @Author taoye
     */
    TradeOrderCoupon getTradeOrderCoupon(TradeOrderCoupon tradeOrderCoupon);
}