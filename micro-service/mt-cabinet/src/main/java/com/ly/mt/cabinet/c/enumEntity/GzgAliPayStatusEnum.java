package com.ly.mt.cabinet.c.enumEntity;

public enum GzgAliPayStatusEnum {
    WAIT_BUYER_PAY("WAIT_BUYER_PAY","等待支付"),
    TRADE_CLOSED("TRADE_CLOSED", "订单关闭"),
    TRADE_SUCCESS("TRADE_SUCCESS", "支付成功"),
    TRADE_FINISHED("TRADE_FINISHED", "支付成功");

    private String key;
    private String value;

    GzgAliPayStatusEnum(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}
