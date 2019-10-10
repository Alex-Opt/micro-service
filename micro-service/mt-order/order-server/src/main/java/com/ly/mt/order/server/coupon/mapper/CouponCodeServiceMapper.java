package com.ly.mt.order.server.coupon.mapper;

import com.ly.mt.core.base.entity.coupon.CouponCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 优惠券信息
 */
@Mapper
public interface CouponCodeServiceMapper {

    /**
     * 根据用户id查询领取的尚未使用的优惠券集合
     * @param userId
     * @param useStatus
     * @return
     */
    List<CouponCode> selectCouponIdByUserIdAndUseStatus(@Param("userId") Long userId, @Param("useStatus") Integer useStatus);


    /**
     * 更新
     * @param userId
     * @param couponId
     * @param useTime
     * @return
     */
    int updateByCouponIdAndUserId(@Param("userId") Long userId, @Param("couponId") Long couponId, @Param("useTime") String useTime);

    /**
     * 插入一条使用优惠券的记录
     * @param couponCode
     * @return
     */
    int insertUsedCouponCodeHistory(CouponCode couponCode);
}
