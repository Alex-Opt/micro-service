package com.ly.mt.home.base.constant;

public enum PaymentModel {
    PAYMENT_MODEL_INTERNET_BANK("1", "网银"),
    PAYMENT_MODEL_WX("2", "微信"),
    PAYMENT_MODEL_AL("3", "阿里"),
    PAYMENT_MODEL_CREDIT_CARD("5", "信用"),
    PAYMENT_MODEL_OTHER("6", "其他"),
    PAYMENT_MODEL_OFFLINE("7", "信用卡");

    private final String id;
    private final String name;

    PaymentModel(String id, String name) {
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
