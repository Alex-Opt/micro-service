package com.ly.mt.core.base.dict;

/**
 * 优惠券使用状态枚举类
 *
 * @author zhanglifeng
 * @date 2019-05-22
 */
public enum CouponUseType {
    COUPON_NOT_USED("1", "使用状态为未使用"),
    COUPON_USED("2", "使用状态为已使用");
    private final String id;
    private final String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    CouponUseType(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
