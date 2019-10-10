package com.ly.mt.cabinet.c.rujin.entity;

public enum RujinDoEnum {
    RUJIN_BIND("0","下货"),
    RUJIN_ONLINE("1","上货"),
    ;

    private String key;
    private String des;

    public String getKey() {
        return key;
    }


    public String getDes() {
        return des;
    }

    RujinDoEnum(String key,  String des) {
        this.key = key;
        this.des = des;
    }
}
