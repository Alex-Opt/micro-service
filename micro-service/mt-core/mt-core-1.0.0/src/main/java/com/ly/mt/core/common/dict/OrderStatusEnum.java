package com.ly.mt.core.common.dict;

/**
 * 订单状态枚举类
 * @author zhanglifeng
 * @date 2019-05-22
 */
public enum OrderStatusEnum {
    ORDER_STATUS_PENDING_PAYMENT("10","等待付款"),
    ORDER_STATUS_PENDING_DELIVERY("20","待配送"),
    ORDER_STATUS_PARTIAL_DELIVERY("25","部分发货"),
    ORDER_STATUS_PENDING_RECEIPT("30","待收货"),
    ORDER_STATUS_COMPLETE("99","订单完成"),;
    private final String id;
    private final String name;

    OrderStatusEnum(String id, String name) {
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
