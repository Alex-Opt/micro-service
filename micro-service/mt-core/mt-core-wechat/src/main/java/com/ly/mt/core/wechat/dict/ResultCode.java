package com.ly.mt.core.wechat.dict;

/**
 * 微信接口result_code枚举
 *
 * @author taoye
 */
public enum ResultCode {
    RESULT_CODE_SUCCESS("SUCCESS", "通信成功"),
    RESULT_CODE_FALL("FAIL", "通信失败");

    private String code;
    private String name;

    ResultCode(String code, String name) {
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