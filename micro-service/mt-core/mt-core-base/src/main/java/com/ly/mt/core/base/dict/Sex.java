package com.ly.mt.core.base.dict;

/**
 * @Description 性别枚举
 * @Author taoye
 */
public enum Sex {
    SEX_FEMALE("0", "男"),
    SEX_MALE("1", "女"),;

    private final String id;
    private final String name;

    Sex(String id, String name) {
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