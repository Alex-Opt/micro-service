package com.ly.mt.core.common.dict;

/**
 * @Description quick_type用户注册来源枚举
 * @Author taoye
 */
public enum QuickTypeEnum {
    QUICKTYPE_M_H5("1", "M端H5商城注册"),
    GZG_B("6","格子柜注册"),

    QUICKTYPE_ACTIVITY_H5("4", "活动H5页面注册");

    private final String id;
    private final String name;

    QuickTypeEnum(String id, String name) {
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