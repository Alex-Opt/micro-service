package com.ly.mt.core.common.dict;

/**
 * @Description 性别枚举
 * @Author taoye
 */
public enum SexEnum {
    SEX_FEMALE("0", "男"),
    SEX_MALE("1", "女"),;

    private final String id;
    private final String name;

    SexEnum(String id, String name) {
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