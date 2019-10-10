package com.ly.mt.cabinet.c.coupon.entity;

public enum GzgCouponUseStateEnum {
    NOT_USED("1","未使用"),
    IS_USED("2","已使用"),
    IS_INVALID("3","已失效"),

    ;
    private String id;
    private String des;

    GzgCouponUseStateEnum(String id, String des){
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
