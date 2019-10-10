package com.ly.mt.core.base.dict;

/**
 * 角色级别枚举
 * basic_role.role_level
 * basic_role_right.right_level
 *
 * @author taoye
 */
public enum RoleLevel {
    ROLE_LEVEL_ONE("1", "一级"),
    ROLE_LEVEL_TWO("2", "二级"),
    ROLE_LEVEL_THREE("3", "三级"),
    ROLE_LEVEL_FOUR("4", "四级"),
    ROLE_LEVEL_FIVE("5", "五级");

    private final String id;
    private final String name;

    RoleLevel(String id, String name) {
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