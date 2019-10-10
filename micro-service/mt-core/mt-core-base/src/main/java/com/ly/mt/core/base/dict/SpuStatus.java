package com.ly.mt.core.base.dict;

/**
 * @Description spu_status枚举
 * @Author taoye
 */
public enum SpuStatus {
    SPU_STATUS_UPPER("1", "上架"),
    SPU_STATUS_LOWER("2", "下架");

    private final String id;
    private final String name;

    SpuStatus(String id, String name) {
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