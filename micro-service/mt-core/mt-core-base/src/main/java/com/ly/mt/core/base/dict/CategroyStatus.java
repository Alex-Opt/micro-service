package com.ly.mt.core.base.dict;

/**
 * 类目有效状态枚举
 * goods_categroy_info.status
 *
 * @author taoye
 */
public enum CategroyStatus {
    CATEGROY_STATUS_YES("1", "有效"),
    CATEGROY_STATUS_NO("2", "删除");

    private final String id;
    private final String name;

    CategroyStatus(String id, String name) {
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
