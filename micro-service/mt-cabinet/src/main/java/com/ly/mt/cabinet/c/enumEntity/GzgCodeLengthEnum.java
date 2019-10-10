package com.ly.mt.cabinet.c.enumEntity;

//格子柜编号长度
public enum GzgCodeLengthEnum {

    GZG_RUJIN(8,"如金格子柜"),
    GZG_YILIAN(15,"亿联格子柜"),
    GZG_SHOWCASE(9,"展柜")
    ;

private int code;
private String desc;

    GzgCodeLengthEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
