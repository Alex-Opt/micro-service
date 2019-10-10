package com.ly.mt.core.base.dict;

/**
 * 商品类别枚举
 *
 * @author taoye
 */
public enum GoodsType {
    GOODS_TYPE_SUIT("1", "套装"),
    GOODS_TYPE_BOMB("2", "烟弹"),
    GOODS_TYPE_SMALL("3", "小烟");

    private final String id;
    private final String name;

    GoodsType(String id, String name) {
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
