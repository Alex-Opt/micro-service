package com.ly.mt.cabinet.c.coupon.entity;

public enum GzgCouponTypeEnum {
    GZG_COUPON_NEW_PEOPLE_DISCOUNT("604336981535113217","格子柜新人八折优惠券对应的id"),
    DAOJIA_COUPON_NEW_PEOPLE_DISCOUNT_1("614125972362903553","到家满150减35新人八折优惠券对应的id"),
    DAOJIA_COUPON_NEW_PEOPLE_DISCOUNT_2("614125972362903554","到家满99减15优惠券对应的id1"),
    DAOJIA_COUPON_NEW_PEOPLE_DISCOUNT_3("614125972362903555","到家满99减15优惠券对应的id2"),
    DAOJIA_COUPON_NEW_PEOPLE_DISCOUNT_4("614125972362903556","到家满99减15优惠券对应的id3"),

    ;
    private String id;
    private String des;

    GzgCouponTypeEnum(String id, String des){
        this.id = id;
        this.des = des;
    }

    public String getId() {
        return id;
    }

    public String getDes() {
        return des;
    }
}
