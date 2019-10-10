package com.ly.mt.core.base.dict;

/**
 * 类目级别枚举
 * goods_categroy_info.lev
 *
 * @author taoye
 */
public enum CategroyLevel {
    CATEGROY_LEVEL_ONE("1", "一级"),
    CATEGROY_LEVEL_TWO("2", "二级"),
    CATEGROY_LEVEL_THREE("3", "三级");

    private final String id;
    private final String name;

    CategroyLevel(String id, String name) {
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
