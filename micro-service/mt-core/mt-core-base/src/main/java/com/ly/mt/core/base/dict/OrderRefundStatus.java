package com.ly.mt.core.base.dict;

/**
 * 订单退货状态枚举类
 * @author zhanglifeng
 * @date 2019-05-22
 */
public enum OrderRefundStatus {
    ORDER_STATUS_REFUND_NO("1","未退货"),
    ORDER_STATUS_REFUND_YES("2","已退货"),;
    private final String id;
    private final String name;

    OrderRefundStatus(String id, String name) {
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
