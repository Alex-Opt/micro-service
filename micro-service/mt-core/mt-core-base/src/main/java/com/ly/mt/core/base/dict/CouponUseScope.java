package com.ly.mt.core.base.dict;

/**
 * 优惠券限制类型枚举类
 *
 * @author zhanglifeng
 * @date 2019-05-22
 */
public enum CouponUseScope {
    COUPON_FOR_ALL_GOODS("1", "优惠券适用于全品类商品"),
    COUPON_FOR_PART_GOODS("2", "优惠券适用于部分商品");
    private final String id;
    private final String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    CouponUseScope(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
