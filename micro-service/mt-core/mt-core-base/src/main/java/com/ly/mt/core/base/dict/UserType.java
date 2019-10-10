package com.ly.mt.core.base.dict;

/**
 * 用户类型枚举类
 *
 * @author zhanglifeng
 */
public enum UserType {
    USER_TYPE_ORDINARY_USER("1", "普通用户"),
    USER_TYPE_SHOP_USER("2", "商家"),
    USER_TYPE_RIDER_USER("3", "骑手");

    private final String id;
    private final String name;

    UserType(String id, String name) {
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
