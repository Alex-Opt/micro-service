package com.ly.mt.core.base.dict;

/**
 * coupon_use_system
 * 优惠券面向的系统
 *
 * @author taoye
 */
public enum CouponUseSystem {
    COUPON_USE_SYSTEM_HOME_C("10", "到家C"),
    COUPON_USE_SYSTEM_CABINET("20", "格子柜"),
    COUPON_USE_SYSTEM_HOME_B("30", "到家B");


    private final String id;
    private final String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    CouponUseSystem(String id, String name) {
        this.id = id;
        this.name = name;
    }
}