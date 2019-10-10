package com.ly.mt.core.base.dict;

/**
 * 退货单是否取消状态
 * @author 484876123@qq.com
 */
public enum OrderRefundCancel {
    ORDER_REFUND_NO_CANCEL("1","未取消"),
    ORDER_REFUND_YES_CANCEL("2","取消"),
    ;
    private final String id;
    private final String name;

    OrderRefundCancel(String id, String name) {
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
