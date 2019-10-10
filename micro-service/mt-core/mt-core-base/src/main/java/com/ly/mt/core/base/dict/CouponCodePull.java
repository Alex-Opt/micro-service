package com.ly.mt.core.base.dict;

/**
 * 优惠券码领取状态枚举类
 * @author zhanglifeng
 */
public enum CouponCodePull {
    COUPON_CODE_PULL_STATUS_NOT_PULL("1", "未领取"),
    COUPON_CODE_PULL_STATUS_PULLED("2", "已领取"),;

    private final String code;
    private final String msg;

    CouponCodePull(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
