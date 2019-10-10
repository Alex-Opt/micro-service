package com.ly.mt.core.dict;

/**
 * @Description 交易类型枚举类
 * @Author taoye
 */
public enum TradeType {
    TRADE_TYPE_PAY("1", "支付"),
    TRADE_TYPE_REFUND("2", "退款");

    private final String id;
    private final String name;

    TradeType(String id, String name) {
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