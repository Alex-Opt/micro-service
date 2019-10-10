package com.ly.mt.core.base.dict;

/**
 * 角色级别枚举-格子柜B端BD角色枚举
 * basic_role.role_level
 * basic_role_right.right_level
 *
 * @author taoye
 */
public enum RoleLevelGzgBD {
    ROLE_LEVEL_GZG_BD_ONE("1", "总经理"),
    ROLE_LEVEL_GZG_BD_TWO("2", "大区经理"),
    ROLE_LEVEL_GZG_BD_THREE("3", "省份经理"),
    ROLE_LEVEL_GZG_BD_FOUR("4", "城市经理"),
    ROLE_LEVEL_GZG_BD_FIVE("5", "BD经理");

    private final String id;
    private final String name;

    RoleLevelGzgBD(String id, String name) {
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