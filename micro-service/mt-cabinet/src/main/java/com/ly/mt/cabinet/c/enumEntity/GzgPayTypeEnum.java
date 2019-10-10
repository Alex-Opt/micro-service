package com.ly.mt.cabinet.c.enumEntity;

public enum GzgPayTypeEnum {
    OTHER("0","其他"),
    WECHAT("1","微信支付"),
    ALIPAY("2","支付宝"),
    QQWALLET("3","QQ钱包"),
    BAIDU_WALLET("4","百度钱包"),
    YINLIAN("5","银联支付"),
    JD("6","京东支付"),
    MEITUAN("7","美团支付"),
    YIDONG("8","中国移动和支付"),
    APPLEPAY("9","ApplePay");
    private String key;
    private String value;

    GzgPayTypeEnum(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
