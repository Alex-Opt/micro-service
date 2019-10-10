package com.ly.mt.core.base.dict;

/**
 * 店铺类型枚举
 * shop_info.shop_type
 *
 * @author taoye
 */
public enum ShopType {
    SHOP_TYPE_OWNER("0", "实体店主"),
    SHOP_TYPE_ASSISTANT("1", "实体店员"),
    SHOP_TYPE_SHOPKEEPERS("2", "电商店主"),
    SHOP_TYPE_SMOKERS("3", "共享烟民"),
    SHOP_TYPE_B("4", "小B"),
    SHOP_TYPE_BUSINESS("5", "兜底商家");


    private final String id;
    private final String name;

    ShopType(String id, String name) {
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
