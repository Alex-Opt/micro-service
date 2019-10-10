package com.ly.mt.core.common.dict;

/**
 * @Description 交易类型枚举类
 * @Author taoye
 */
public enum TradeTypeEnum {
    TRADE_TYPE_PAY("1", "支付"),
    TRADE_TYPE_REFUND("2", "退款");

    private final String id;
    private final String name;

    TradeTypeEnum(String id, String name) {
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