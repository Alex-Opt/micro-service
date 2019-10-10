package com.ly.mt.cabinet.b.common.dict;

import org.apache.commons.lang.StringUtils;

/**
 * 是否押金
 */
public enum IsDepositEnum {
    A(46,"是"),
    B(47,"否");

    int key;
    String value;

    IsDepositEnum(int key,String value){
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getByKey(int key){
        for (IsDepositEnum enumValue : values()){
            if (enumValue.getKey() == key){
                return enumValue.getValue();
            }
        }
        return null;
    }

    public static int getByValue(String value){
        for (IsDepositEnum entry : values()){
            if (StringUtils.equals(entry.getValue(),value)){
                return entry.getKey();
            }
        }
        return -1;
    }
}
