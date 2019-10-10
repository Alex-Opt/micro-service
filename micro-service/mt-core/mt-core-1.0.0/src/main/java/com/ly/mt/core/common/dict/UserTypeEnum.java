package com.ly.mt.core.common.dict;

/**
 * @author zhanglifeng
 * 用户类型枚举类
 */
public enum UserTypeEnum {
    USER_TYPE_ORDINARY_USER("1","普通用户"),
    USER_TYPE_SHOP_USER("2","商家"),
    USER_TYPE_RIDER_USER("3","骑手"),
    USER_TYPE_SYSTEM_USER("4","系统用户");
    private final String id;
    private final String name;

    UserTypeEnum(String id, String name) {
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
