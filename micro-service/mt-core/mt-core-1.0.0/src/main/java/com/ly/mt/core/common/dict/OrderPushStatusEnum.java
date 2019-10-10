package com.ly.mt.core.common.dict;

/**
 * 订单推送状态枚举类
 * @author zhanglifeng
 * @date 2019-05-22
 */
public enum OrderPushStatusEnum {
    ORDER_PUSH_STATUS_NOT_PUSH("1","订单未推送"),
    ORDER_PUSH_STATUS_PUSH_SUCCESS("2","订单推送成功"),
    ORDER_PUSH_STATUS_PUSH_FAIL("3","订单推送失败");
    private final String id;
    private final String name;

    OrderPushStatusEnum(String id, String name) {
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
