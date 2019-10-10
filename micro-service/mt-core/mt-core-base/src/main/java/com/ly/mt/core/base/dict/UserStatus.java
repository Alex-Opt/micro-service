package com.ly.mt.core.base.dict;

/**
 * @Description user_status用户状态枚举
 * @Author taoye
 */

/**
 * @deprecated 使用ValidStatus枚举
 */
public enum UserStatus {
    USER_STATUS_USING("10", "正常"),
    USER_STATUS_STOP_USING("20", "停用"),;

    private final String id;
    private final String name;

    UserStatus(String id, String name) {
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