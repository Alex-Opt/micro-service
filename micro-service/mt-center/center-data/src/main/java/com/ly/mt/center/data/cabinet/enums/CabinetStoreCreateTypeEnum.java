package com.ly.mt.center.data.cabinet.enums;

/**
 * 格子柜创建类型  1:格子柜  2:展柜
 */
public enum CabinetStoreCreateTypeEnum {
    A(1,"格子柜"),
    B(2,"展柜");

    private int key;
    private String value;

    CabinetStoreCreateTypeEnum(int key,String value){
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
}
