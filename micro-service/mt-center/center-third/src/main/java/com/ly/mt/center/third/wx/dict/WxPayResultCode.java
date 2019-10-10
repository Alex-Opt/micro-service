package com.ly.mt.center.third.wx.dict;

/**
 * @Description 微信支付反参code枚举类
 * @Author taoye
 */
public enum WxPayResultCode {
    WX_PAY_RESULT_CODE_SUCCESS("SUCCESS", "成功"),
    WX_PAY_RESULT_CODE_FALL("FAIL", "失败");

    private String code;
    private String name;

    WxPayResultCode(String code, String name) {
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