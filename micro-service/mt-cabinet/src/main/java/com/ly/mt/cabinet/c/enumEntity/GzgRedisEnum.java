package com.ly.mt.cabinet.c.enumEntity;

/**
 * @Description redis缓存key枚举
 * @Author taoye
 */
public enum GzgRedisEnum {
    GZG_DEVICE_TOKEN_REDIS("gzg_device_cache_token_key", "格子柜登录亿联获取的token"),

    ;

    private final String key;
    private final String describe;

    GzgRedisEnum(String key, String describe) {
        this.key = key;
        this.describe = describe;
    }

    public String getKey() {
        return key;
    }
}