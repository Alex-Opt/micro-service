package com.ly.mt.core.common.dict;

/**
 * 优惠活动的名称 枚举类
 * 优惠活动类型：1-参加优惠活动(activity_info表),2-使用优惠券(coupon_info表)
 * @author zhanglifeng
 * @date 2019-05-22
 */
public enum CouponActivityTypeEnum {
    COUPON_ACTIVITY_TYPE_ALL("-1", "全部"),
    COUPON_ACTIVITY_TYPE_FALL("1","直降"),
    COUPON_ACTIVITY_TYPE_FULL_REDUCTION("2","满减"),
    COUPON_ACTIVITY_TYPE_SECOND_GRAB("3","秒杀"),
    COUPON_ACTIVITY_TYPE_DISCOUNT("4","折扣"),
    COUPON_ACTIVITY_TYPE_GROUP_BUY("5","团购"),
    COUPON_ACTIVITY_TYPE_COUPON("6","优惠券");
    private final String id;
    private final String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    CouponActivityTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
