package com.ly.mt.core.wechat.dict;

/**
 * 微信接口scene_id枚举
 *
 * @author taoye
 */
public enum ProductType {
    PRODUCT_1("PRODUCT_1", "商品促销"),
    PRODUCT_2("PRODUCT_2", "抽奖"),
    PRODUCT_3("PRODUCT_3", "虚拟物品兑奖"),
    PRODUCT_4("PRODUCT_4", "企业内部福利"),
    PRODUCT_5("PRODUCT_5", "渠道分润"),
    PRODUCT_6("PRODUCT_6", "保险回馈"),
    PRODUCT_7("PRODUCT_7", "彩票派奖"),
    PRODUCT_8("PRODUCT_8", "税务刮奖");

    private final String code;
    private final String name;

    ProductType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}