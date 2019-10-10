package com.ly.mt.core.common.dict;

/**
 * @Description user_status用户状态枚举
 * @Author taoye
 */
public enum UserStatusEnum {
    USER_STATUS_USING("10", "正常"),
    USER_STATUS_STOP_USING("20", "停用"),;

    private final String id;
    private final String name;

    UserStatusEnum(String id, String name) {
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