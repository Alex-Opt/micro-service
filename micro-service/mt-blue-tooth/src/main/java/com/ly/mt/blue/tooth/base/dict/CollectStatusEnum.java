package com.ly.mt.blue.tooth.base.dict;

/**
 * @Description 收藏状态
 * @Author whl
 */
public enum CollectStatusEnum {
    COLLECT("1", "收藏"),
    NO_COLLECT("0", "未收藏");

    private final String id;
    private final String name;

    CollectStatusEnum(String id, String name) {
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