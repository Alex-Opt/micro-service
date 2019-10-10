package com.ly.mt.core.base.dict;

/**
 * @Description data_source枚举
 * @Author taoye
 */
public enum DataSource {
    DATA_SOURCE_GY("1", "管易");

    private final String id;
    private final String name;

    DataSource(String id, String name) {
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