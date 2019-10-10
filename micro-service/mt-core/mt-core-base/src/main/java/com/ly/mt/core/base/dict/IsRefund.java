package com.ly.mt.core.base.dict;

/**
 * 订单是否退货枚举类
 * trade_orders.is_refund
 *
 * @author taoye
 */
public enum IsRefund {
    IS_REFUND_NO("1", "否"),
    IS_REFUND_EXAMINE("2", "退货审核"),
    IS_REFUND_DONE("3", "退货完成");

    private final String id;
    private final String name;

    IsRefund(String id, String name) {
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
