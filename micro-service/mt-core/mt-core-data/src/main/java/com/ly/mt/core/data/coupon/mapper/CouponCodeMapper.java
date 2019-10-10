package com.ly.mt.core.data.coupon.mapper;

import com.ly.mt.core.data.coupon.entity.CouponCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * CouponCode操作接口
 *
 * @author taoye
 */
@Mapper
public interface CouponCodeMapper {
    /**
     * 更新CouponCode
     *
     * @param couponCode 更新条件和数据
     * @author taoye
     */
    void updateCouponCode(CouponCode couponCode);

    /**
     * 更新优惠券（取消时返还）
     *
     * @param ids
     * @author linan
     */
    void updateCouponCodeBatch(List<String> ids);

    /**
     * 退还优惠券为未使用
     * @param couponCode
     */
    void updateCouponCodeByUserIdAndCouponId(CouponCode couponCode);
}