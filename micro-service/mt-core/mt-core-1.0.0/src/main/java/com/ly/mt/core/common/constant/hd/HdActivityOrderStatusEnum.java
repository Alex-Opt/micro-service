package com.ly.mt.core.common.constant.hd;

/**
 * 活动订单状态枚举
 * 订单状态(FINISH完结，CANCEL取消,UNPAY待支付，OKPAY已支付，SEND已发货)
 */
public enum HdActivityOrderStatusEnum {
    FINISH("FINISH","完结"),
    CANCEL("CANCEL","取消"),
    UNPAY("UNPAY","待支付"),
    OKPAY("OKPAY","已经支付"),
    SEND已("SEND","待发货"),
    ;


    HdActivityOrderStatusEnum(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private String status;

    private  String msg;

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
