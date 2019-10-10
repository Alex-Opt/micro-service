package com.ly.mt.core.common.dict;

/**
 * @Description 支付方式枚举类
 * @Author taoye
 */
public enum PaymentTypeEnum {
    PAYMENT_TYPE_ALIPAY("1","阿里"),
    PAYMENT_TYPE_WXPAY("2","微信");

    private final String id;
    private final String name;

    PaymentTypeEnum(String id, String name) {
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
