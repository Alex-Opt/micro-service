package com.ly.mt.core.common.dict;

/**
 * 使用优惠活动类型枚举类
 *
 * @author zhanglifeng
 * @date 2019-05-22
 */
public enum CouponTypeEnum {
    COUPON_TYPE_ACTIVITY("1", "参加优惠活动"),
    COUPON_TYPE_COUPON("2", "使用优惠券"),;
    private final String id;
    private final String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    CouponTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
