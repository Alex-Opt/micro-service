package com.ly.mt.core.base.dict;

/**
* @description: 补货订单类型
* @author: wanghongliang
* @create: 2019/9/9 15:37
**/
public enum ReplenishOrderTypeEnum {
    BD_ORDER("0","BD补货单"),
    OWEN_ORDER("1","店铺管理员补货单");

    String key;
    String value;

    ReplenishOrderTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }}

