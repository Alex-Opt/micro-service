package com.ly.mt.blue.tooth.base.dict;

/**
 * @Description 短信验证码类型
 * @Author whl
 */
public enum SmsTypeEnum {
    REGISTER("1", "注册"),
    RESETPASSWORD("2", "重置密码"),
    LOGIN("3", "登录验证"),
    OLDPHONE("4", "更换绑定手机号-往旧手机号发送验证码"),
    NEWPHONE("5", "更换绑定手机号-新手机号发送验证码");

    private final String id;
    private final String name;

    SmsTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}