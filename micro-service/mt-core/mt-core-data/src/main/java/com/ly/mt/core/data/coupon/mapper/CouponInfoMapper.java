package com.ly.mt.core.data.coupon.mapper;

import com.ly.mt.core.data.coupon.entity.CouponInfo;
import com.ly.mt.core.data.trade.entity.TradeOrderCoupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * CouponInfo操作接口
 *
 * @author taoye
 */
@Mapper
public interface CouponInfoMapper {
    /**
     * 查询CouponInfo
     *
     * @author taoye
     */
    CouponInfo getCouponInfo(CouponInfo couponInfo);
}