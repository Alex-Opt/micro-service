package com.ly.mt.core.common.dict;

/**
 * 店铺状态枚举常量
 */
public enum ShopUserStatusEnum {

    SHOP_USER_STATUS_USING("1", "正常"),
    SHOP_USER_STATUS_STOP_USING("2", "停用"),;
    private final String id;
    private final String name;

    ShopUserStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }}
