package com.ly.mt.cabinet.c.rujin.entity;

public enum RujinUrlEnum {
    RUJIN_BIND("1","https://leiyan.allseller.cn/backs/termapi/bind.html","绑定终端设备信息"),
    RUJIN_HGLIST("2","https://leiyan.allseller.cn/backs/termapi/hglist.html","查询终端货柜列表"),
    RUJIN_HGSTATE("3","https://leiyan.allseller.cn/backs/termapi/hgstate.html","操作终端货柜状态"),
    RUJIN_ONLINE("4","https://leiyan.allseller.cn/backs/termapi/online.html","查询终端在线状态"),
    RUJIN_ORDER("5","https://leiyan.allseller.cn/backs/termapi/order.html","订单开门请求"),
    RUJIN_SERVER("7","https://leiyan.allseller.cn/backs/termapi/server.html","维护打开门"),
    ;

    private String key;
    private String value;
    private String des;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getDes() {
        return des;
    }

    RujinUrlEnum(String key, String value, String des) {
        this.key = key;
        this.value = value;
        this.des = des;
    }
}
