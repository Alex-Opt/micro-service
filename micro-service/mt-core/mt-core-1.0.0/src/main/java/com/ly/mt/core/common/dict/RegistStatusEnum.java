package com.ly.mt.core.common.dict;

/**
 * @Description 用户注册状态枚举
 * @Author taoye
 */
public enum RegistStatusEnum {
    REGIST_STATUS_NO("0", "未注册"),
    REGIST_STATUS_YES("1", "已注册");

    private final String id;
    private final String name;

    RegistStatusEnum(String id, String name) {
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