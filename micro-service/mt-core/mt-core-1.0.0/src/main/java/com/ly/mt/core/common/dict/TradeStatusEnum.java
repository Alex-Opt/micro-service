package com.ly.mt.core.common.dict;

/**
 * @Description 交易状态枚举类
 * @Author taoye
 */
public enum TradeStatusEnum {
    TRADE_STATUS_PAY_CREATE("10", "支付创建"),
    TRADE_STATUS_PAY_FALL("20", "支付失败"),
    TRADE_STATUS_PAY_SUCCESS("30", "支付成功"),
    TRADE_STATUS_REFUND_CREATE("40", "退款创建"),
    TRADE_STATUS_REFUND_FALL("50", "退款失败"),
    TRADE_STATUS_REFUND_SUCCESS("60", "退款成功");

    private final String id;
    private final String name;

    TradeStatusEnum(String id, String name) {
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