package com.ly.mt.core.base.dict;

/**
 * 上传记录类型枚举
 * record_import.import_type
 *
 * @author taoye
 */
public enum ImportType {
    IMPORT_TYPE_GOODS_SKU_CODE("1", "GoodsSkuCode");

    private final String id;
    private final String name;

    ImportType(String id, String name) {
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