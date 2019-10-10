package com.ly.mt.blue.tooth.base.dict;

/**
 * @Description 密码状态
 * @Author whl
 */
public enum PasswordStatusEnum {
    PASSWORDSET("0", "已设置"),
    PASSWORDNOSET("1", "未设置");


    private final String id;
    private final String name;

    PasswordStatusEnum(String id, String name) {
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