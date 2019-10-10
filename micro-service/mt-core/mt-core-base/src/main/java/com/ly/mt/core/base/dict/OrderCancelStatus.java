package com.ly.mt.core.base.dict;

/**
 * 订单取消状态枚举类
 * @author zhanglifeng
 * @date 2019-05-22
 */
public enum OrderCancelStatus {
    ORDER_CANCEL_STATUS_NO("1","未取消订单"),
    ORDER_CANCEL_STATUS_YES("2","已取消订单"),;
    private final String id;
    private final String name;

    OrderCancelStatus(String id, String name) {
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
