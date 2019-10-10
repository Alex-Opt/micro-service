package com.ly.mt.core.base.dict;

/**
 * 使用优惠活动类型枚举类
 *
 * @author zhanglifeng
 * @date 2019-05-22
 */
public enum CouponType {
    COUPON_TYPE_ACTIVITY("1", "参加促销活动"),
    COUPON_TYPE_COUPON("2", "使用优惠券");
    private final String id;
    private final String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    CouponType(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
