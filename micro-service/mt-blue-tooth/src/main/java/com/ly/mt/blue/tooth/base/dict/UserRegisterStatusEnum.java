package com.ly.mt.blue.tooth.base.dict;

/**
 * @Description 用户是否注册
 * @Author whl
 */
public enum UserRegisterStatusEnum {
    UNREGISTER("0", "未注册"),
    REGISTER("1", "已注册");

    private final String id;
    private final String name;

    UserRegisterStatusEnum(String id, String name) {
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