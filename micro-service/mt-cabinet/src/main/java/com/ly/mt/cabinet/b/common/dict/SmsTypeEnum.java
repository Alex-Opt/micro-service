package com.ly.mt.cabinet.b.common.dict;

/**
 * @Description 短信验证码类型
 * @Author whl
 */
public enum SmsTypeEnum {
    LOGIN("1", "登录验证"),
    NOTIFICATION_GZG_BD("2", "格子柜B端-格子柜BD补货通知"),
    NOTIFICATION_ZG_BD("3", "格子柜B端-展柜BD补货通知");
    private final String id;
    private final String name;

    SmsTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}