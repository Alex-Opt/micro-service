package com.ly.mt.core.base.dict;

/**
 * 用户认证枚举
 */
public enum UserCertifacationStatus {

    USER_CERTIFICATION_NOT("0","未认证"),
    USER_CERTIFICATION_ADULT("1","已认证"),
    USER_CERTIFICATION_YOUNG("2","未成年");

    private final String id;
    private final String name;

    UserCertifacationStatus(String id, String name) {
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
