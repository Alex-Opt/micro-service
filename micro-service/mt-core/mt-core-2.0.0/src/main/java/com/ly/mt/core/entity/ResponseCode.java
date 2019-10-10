package com.ly.mt.core.entity;

/**
 * @Description 响应编码
 * @Author taoye
 */
public enum ResponseCode {
    RESPONSE_CODE_SUCCESS("0", "操作成功"),
    RESPONSE_CODE_ERROR("1", "系统异常"),
    RESPONSE_CODE_NOT_LOGIN("3", "未登录"),
    RESPONSE_CODE_NOT_REGIST("4", "未注册");

    private String code;
    private String msg;

    ResponseCode(String code, String msg) {
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