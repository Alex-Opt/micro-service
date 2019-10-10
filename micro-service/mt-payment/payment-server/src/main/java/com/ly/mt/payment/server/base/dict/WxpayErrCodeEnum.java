package com.ly.mt.payment.server.base.dict;

/**
 * @Description 阿里支付反参code枚举类
 * @Author taoye
 */
public enum WxpayErrCodeEnum {
    WXPAY_ERR_CODE_ORDER_NOT_EXIST("ORDERNOTEXIST", "订单不存在");

    private String code;
    private String name;

    WxpayErrCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}