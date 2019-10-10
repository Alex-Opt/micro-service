package com.ly.mt.core.common.constant.gzg;

public enum  UserRoleEnum {
    A(0,"运营"),
    B(1,"酒店管理"),
    C(2,"补货员");

    private int key;
    private String value;

    UserRoleEnum(int key,String value){
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }
}
