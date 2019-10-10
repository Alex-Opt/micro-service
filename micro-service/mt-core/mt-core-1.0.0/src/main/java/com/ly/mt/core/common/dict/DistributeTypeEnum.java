package com.ly.mt.core.common.dict;

/**
 * @Description 配送方式枚举
 * @Author taoye
 */
public enum DistributeTypeEnum {
    DISTRIBUTE_TYPE_ONE_HOUR("1","一小时达", "", "9:0-100,5:100-150"),
    DISTRIBUTE_TYPE_CITY_EXPRESS("2","同城快递,次日达","9", ""),
    DISTRIBUTE_TYPE_ORDINARY_EXPRESS("3","普通快递", "5", "");
    /*这个如果放开，便会在c端页面显示，业务上c端没有货到付款。但在活动页存在这个值 DISTRIBUTE_TYPE_CASH_ON_DELIVERY("4","货到付款", "0"),*/

    private final String id;
    private final String name;
    private final String price;
    private final String rule;

    DistributeTypeEnum(String id, String name, String price, String rule) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rule = rule;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getRule() {
        return rule;
    }}
