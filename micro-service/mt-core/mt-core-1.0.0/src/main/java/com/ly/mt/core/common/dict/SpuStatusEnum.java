package com.ly.mt.core.common.dict;

/**
 * @Description spu_status枚举
 * @Author taoye
 */
public enum SpuStatusEnum {
    SPU_STATUS_YES("1", "上架"),
    SPU_STATUS_NO("2", "下架");

    private final String id;
    private final String name;

    SpuStatusEnum(String id, String name) {
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