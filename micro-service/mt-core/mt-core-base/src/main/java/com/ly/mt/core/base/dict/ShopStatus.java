package com.ly.mt.core.base.dict;

/**
 * 店铺状态枚举类
 * shop_info.status
 *
 * @author taoye
 */
public enum ShopStatus {
    SHOP_STATUS_YES("0", "正常"),
    SHOP_STATUS_NO("1", "冻结");

    private final String id;
    private final String name;

    ShopStatus(String id, String name) {
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
