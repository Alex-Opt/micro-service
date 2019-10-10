package com.ly.mt.core.common.dict;

/**
 * @Description data_source枚举
 * @Author taoye
 */
public enum DataSourceEnum {
    DATA_SOURCE_GY("1", "管易");

    private final String id;
    private final String name;

    DataSourceEnum(String id, String name) {
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