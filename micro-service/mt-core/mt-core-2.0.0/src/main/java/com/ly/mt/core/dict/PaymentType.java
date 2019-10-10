package com.ly.mt.core.dict;

/**
 * @Description 支付方式枚举类
 * @Author taoye
 */
public enum PaymentType {
    PAYMENT_TYPE_AL("1","阿里"),
    PAYMENT_TYPE_WX("2","微信");

    private final String id;
    private final String name;

    PaymentType(String id, String name) {
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
