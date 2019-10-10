package com.ly.mt.core.base.dict;

/**
 * 订单类型枚举类
 *
 * @author zhanglifeng
 * @date 2019-05-27
 */
public enum OrderType {
    ORDER_TYPE_ORDINARY_ORDER("1", "普通订单"),
    ORDER_TYPE_EXCLUSIVE_ORDER("2", "专属订单"),
    ORDER_TYPE_ACTIVITY_ORDER("3", "活动订单"),
    ORDER_TYPE_PURCHASE_ORDER("4", "进货订单");

    private final String id;
    private final String name;

    OrderType(String id, String name) {
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
