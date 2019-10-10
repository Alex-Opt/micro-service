package com.ly.mt.core.common.dict;

/**
 * @Description 是否枚举
 * @Author taoye
 */
public enum WhetherEnum {
    WHETHER_NO("0", "否"),
    WHETHER_YES("1", "是");

    private final String id;
    private final String name;

    WhetherEnum(String id, String name) {
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