package com.ly.mt.cabinet.b.common.dict;

public enum RedisEnum {
    LOGIN_TOKEN("cabinet:login:token:", "login token"),
    LOGIN_CODE("cabinet:login:code:", "login code"),
    OPTION_CACHE("cabinet:options:", "cooperation options");


    private final String key;
    private final String desc;

    RedisEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }
}
