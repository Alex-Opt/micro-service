package com.ly.mt.core.base.dict;

/**
 * 角色类型枚举
 * basic_role.role_type
 *
 * @author taoye
 */
public enum RoleType {
    /**
     * 格子柜B端BD角色
     */
    ROLE_TYPE_GB_BD("1", "BD"),
    /**
     * 格子柜B端仓库角色
     */
    ROLE_TYPE_GB_WAREHOUSE("2", "仓库"),

    /**
     * 格子柜B端店铺管理员
     */
    ROLE_TYPE_GB_SHOPMANAGER("3", "店铺管理员"),

    /**
     * 格子柜B端 店铺员工
     */
    ROLE_TYPE_GB_SHOP_EMP("4", "店铺员工"),

    /**
     * 未知角色
     */
    ROLE_TYPE_GB_UNKNOWN("5", "未知角色");

    private final String id;
    private final String name;

    RoleType(String id, String name) {
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
