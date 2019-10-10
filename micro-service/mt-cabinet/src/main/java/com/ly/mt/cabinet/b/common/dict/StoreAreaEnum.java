package com.ly.mt.cabinet.b.common.dict;

import org.apache.commons.lang.StringUtils;

/**
 * 店铺面积
 */
public enum StoreAreaEnum {
    A(42,"50平米以下"),
    B(43,"50-100平米"),
    C(44,"100-200平米"),
    D(45,"200平米以上")
    ;

    int key;
    String value;

    StoreAreaEnum(int key, String value){
        this.key = key;
        this.value = value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static String getByKey(int key){
        for (StoreAreaEnum e : values()){
            if (e.getKey() == key){
                return e.getValue();
            }
        }
        return null;
    }

    public static int getByValue(String val) {
        for (StoreAreaEnum entry : values()){
            if (StringUtils.equals(entry.getValue(), val)){
                return entry.getKey();
            }
        }
        return -1;
    }
}
