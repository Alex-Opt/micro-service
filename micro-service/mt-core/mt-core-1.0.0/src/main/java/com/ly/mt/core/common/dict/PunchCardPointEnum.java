package com.ly.mt.core.common.dict;

public enum PunchCardPointEnum {

    PUNCH_CARD_POINT_STATUS_DISABLE(2, "无效"),
    PUNCH_CARD_POINT_STATUS_ENABLE(1, "有效");

    private final Integer code;
    private final String msg;

    PunchCardPointEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
