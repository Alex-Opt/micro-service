package com.ly.mt.core.base.dict;

public enum PunchCardCoupon {

    PUNCH_CARD_COUPON_STATUS_DISABLE(2, "无效"),
    PUNCH_CARD_COUPON_STATUS_ENABLE(1, "有效");

    private final Integer code;
    private final String msg;

    PunchCardCoupon(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
