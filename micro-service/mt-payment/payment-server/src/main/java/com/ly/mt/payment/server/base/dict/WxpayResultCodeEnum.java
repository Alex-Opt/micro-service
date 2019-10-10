package com.ly.mt.payment.server.base.dict;

/**
 * @Description 阿里支付反参code枚举类
 * @Author taoye
 */
public enum WxpayResultCodeEnum {
    WXPAY_RESULT_CODE_SUCCESS("SUCCESS", "成功"),
    WXPAY_RESULT_CODE_FALL("FAIL", "失败");

    private String code;
    private String name;

    WxpayResultCodeEnum(String code, String name) {
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