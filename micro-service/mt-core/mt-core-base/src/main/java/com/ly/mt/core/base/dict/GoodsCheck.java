package com.ly.mt.core.base.dict;

/**
 * 商品校验的结果枚举类
 */
public enum GoodsCheck {
    CHECK_GOODS_FAILURE("false","商品校验未通过"),
    CHECK_GOODS_SECCESS("true","商品校验通过");
    private final String id;
    private final String name;

    GoodsCheck(String id, String name) {
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
