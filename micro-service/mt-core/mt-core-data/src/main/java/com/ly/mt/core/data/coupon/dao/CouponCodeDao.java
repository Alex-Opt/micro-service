package com.ly.mt.core.data.coupon.dao;

import com.ly.mt.core.data.coupon.entity.CouponCode;

import java.util.List;
import java.util.Map;

/**
 * CouponCode操作接口
 *
 * @author taoye
 */
public interface CouponCodeDao {
    /**
     * 更新CouponCode
     *
     * @param couponCode 更新条件和数据
     * @author taoye
     */
    void updateCouponCode(CouponCode couponCode);

    /**
     * 更新优惠券（取消时返还）
     * @param ids
     * @author linan
     */
    void updateCouponCodeBatch(List<String> ids);

    /**
     * 到家C，批量更新取消的订单优惠券返还
     * @author zhanglifeng
     * @param list
     * @return
     */
    void batchUpdateCouponCodeUnused(List<Map<String,String>> list);
}