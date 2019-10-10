package com.ly.mt.core.dict;

/**
 * @Description quick_type用户注册来源枚举
 * @Author taoye
 */
public enum QuickType {
    QUICK_TYPE_MALL_H5("1", "H5商城"),
    QUICK_TYPE_GZG_B("6","格子柜B"),
    QUICK_TYPE_BLUETOOTH("7","蓝牙APP");
    private final String id;
    private final String name;

    QuickType(String id, String name) {
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