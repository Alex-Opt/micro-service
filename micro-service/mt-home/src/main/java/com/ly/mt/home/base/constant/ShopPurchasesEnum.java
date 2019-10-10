package com.ly.mt.home.base.constant;

public enum ShopPurchasesEnum {
    SHOP_PURCHASES_TO_PAY("10", "待付款"),
    SHOP_PURCHASES_CANCEL("15", "已取消"),
    SHOP_PURCHASES_TO_SEND("20", "待发货"),
    SHOP_PURCHASES_SEND_PART("25", "部分发货"),
    SHOP_PURCHASES_TO_RECEIVE("30", "待收货"),
    SHOP_PURCHASES_FINISH("99", "完成");

    private String code;
    private String desc;

    ShopPurchasesEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
