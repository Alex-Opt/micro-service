package com.ly.mt.core.wechat.dict;

/**
 * 微信接口return_code枚举
 *
 * @author taoye
 */
public enum ReturnCode {
    RETURN_CODE_SUCCESS("SUCCESS", "通信成功"),
    RETURN_CODE_FALL("FAIL", "通信失败");

    private String code;
    private String name;

    ReturnCode(String code, String name) {
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