package com.ly.mt.center.third.wx.dict;

/**
 * @Description 阿里支付反参code枚举类
 * @Author taoye
 */
public enum WxPayErrorCode {
    WX_PAY_ERR_CODE_ORDER_NOT_EXIST("ORDERNOTEXIST", "订单不存在");

    private String code;
    private String name;

    WxPayErrorCode(String code, String name) {
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