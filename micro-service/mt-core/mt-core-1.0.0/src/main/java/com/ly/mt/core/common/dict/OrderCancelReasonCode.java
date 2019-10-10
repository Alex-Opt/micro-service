package com.ly.mt.core.common.dict;

/**
 * 订单取消原因代码
 *
 * @author zhanglifeng
 */
public enum OrderCancelReasonCode {
    USER_CANCEL("1","用户取消"),
    SHOP_CANCEL("2","商家取消"),;
    private final String id;
    private final String name;

    OrderCancelReasonCode(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }}
