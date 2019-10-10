package com.ly.mt.center.data.cabinet.enums;

public enum CabinetTypeEnum {

    A(1,"亿联"),
    B(2,"展柜"),
    C(3,"如金");

    private int key;
    private String value;

    CabinetTypeEnum(int key,String value){
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
