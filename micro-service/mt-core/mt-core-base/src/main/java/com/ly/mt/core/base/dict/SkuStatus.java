package com.ly.mt.core.base.dict;

/**
 * @Description spu_status枚举
 * @Author taoye
 */
public enum SkuStatus {
    SKU_STATUS_YES("1", "正常"),
    SKU_STATUS_NO("2", "停用");

    private final String id;
    private final String name;

    SkuStatus(String id, String name) {
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