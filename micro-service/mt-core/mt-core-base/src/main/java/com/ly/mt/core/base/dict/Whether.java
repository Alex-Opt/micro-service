package com.ly.mt.core.base.dict;

/**
 * @Description 是否枚举
 * @Author taoye
 */
public enum Whether {
    WHETHER_NO("0", "否"),
    WHETHER_YES("1", "是");

    private final String id;
    private final String name;

    Whether(String id, String name) {
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