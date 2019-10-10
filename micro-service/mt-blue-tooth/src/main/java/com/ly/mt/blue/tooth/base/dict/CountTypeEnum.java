package com.ly.mt.blue.tooth.base.dict;

/**
 * @Description 统计类型
 * @Author whl
 */
public enum CountTypeEnum {
    DAY("1", "天"),
    WEEK("2", "周"),
    MONTH("3", "月");

    private final String id;
    private final String name;

    CountTypeEnum(String id, String name) {
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