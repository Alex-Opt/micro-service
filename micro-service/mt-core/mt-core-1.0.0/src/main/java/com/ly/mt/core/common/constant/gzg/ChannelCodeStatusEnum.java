package com.ly.mt.core.common.constant.gzg;

public enum ChannelCodeStatusEnum {
    NOT_SELLED(0,"未出售"),
    ELLED(1,"已出售")

    ;
    private int code;
    private String msg;

    ChannelCodeStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
