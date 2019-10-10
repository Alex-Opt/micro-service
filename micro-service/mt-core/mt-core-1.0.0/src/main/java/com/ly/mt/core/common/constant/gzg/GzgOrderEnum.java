package com.ly.mt.core.common.constant.gzg;

public enum GzgOrderEnum {

    GZG_ORDER_STATE_10(10,"待支付"),
    GZG_ORDER_STATE_99(99,"已完成"),
    GZG_ORDER_STATE_100(100,"已退款"),

    ;
    private int code;
    private String msg;

    GzgOrderEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Integer code){
        if (GZG_ORDER_STATE_10.code == code){
            return GZG_ORDER_STATE_10.msg;
        }
        if (GZG_ORDER_STATE_99.code == code){
            return GZG_ORDER_STATE_99.msg;
        }
        if (GZG_ORDER_STATE_100.code == code){
            return GZG_ORDER_STATE_100.msg;
        }
        return "not enum";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
