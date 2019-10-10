package com.ly.mt.core.common.dict;

/**
 * 店铺类型枚举常量
 */
public enum ShopTypeEnum {
    SHOP_ENTITY_OWNER("0", "实体店主"),
    SHOP_ENTITY_ASSISTANT("1", "实体店员"),
    SHOP_ELECTRIC_SHOPKEEPERS("2","电商店主"),
    SHOP_SHARING_SMOKERS("3","共享烟民"),
    SHOP_SMALL_B("4","小B"),
    SHOP_DEFAULT("5","兜底商家");


    private final String id;
    private final String name;

    ShopTypeEnum(String id, String name) {
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
