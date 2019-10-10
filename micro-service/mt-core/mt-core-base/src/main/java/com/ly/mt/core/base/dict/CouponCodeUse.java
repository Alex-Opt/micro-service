package com.ly.mt.core.base.dict;

/**
 * 优惠券码使用状态枚举类
 * @author zhanglifeng
 * @date 2019-06-14
 */
public enum CouponCodeUse {
    COUPON_CODE_USE_STATUS_NOT_USE("1", "未使用"),
    COUPON_CODE_USE_STATUS_USED("2", "已使用"),;

    private final String code;
    private final String msg;

    CouponCodeUse(String code, String msg){
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
