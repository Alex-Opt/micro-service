package com.ly.mt.core.base.dict;

/**
 * 进货单优惠类型枚举
 *
 * @author taoye
 */
public enum DiscountType {
    DISCOUNT_TYPE_LADDER("1", "阶梯价优惠"),
    DISCOUNT_TYPE_VIP("2", "会员价优惠"),
    DISCOUNT_TYPE_COUPON("2", "优惠券优惠");

    private final String id;
    private final String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    DiscountType(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
