package com.ly.mt.core.base.dict;

/**
 * 订单推送状态枚举类
 * trade_orders.push_status
 *
 * @author taoye
 */
public enum PushStatus {
    PUSH_STATUS_NOT_PUSH("1", "订单未推送"),
    PUSH_STATUS_PUSH_SUCCESS("2", "订单推送成功"),
    PUSH_STATUS_PUSH_FAIL("3", "订单推送失败");

    private final String id;
    private final String name;

    PushStatus(String id, String name) {
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
