package com.ly.mt.core.base.dict;

/**
 * 订单种类枚举
 *
 * @author taoye
 */
public enum OrderCategory {
    ORDER_CATEGORY_HOME_C("1", "MOTI到家"),
    ORDER_CATEGORY_HOME_B("2", "MOTI商家端"),
    ORDER_CATEGORY_CABINET_C("11", "MOTI售卖柜");

    private final String id;
    private final String name;

    OrderCategory(String id, String name) {
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