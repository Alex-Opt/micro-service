package com.ly.mt.core.base.dict;

/**
 * 权限类型枚举
 * basic_role_right.right_type
 *
 * @author taoye
 */
public enum RightType {
    RIGHT_TYPE_FUNC("1", "功能"),
    RIGHT_TYPE_AREA("2", "区域");

    private final String id;
    private final String name;

    RightType(String id, String name) {
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