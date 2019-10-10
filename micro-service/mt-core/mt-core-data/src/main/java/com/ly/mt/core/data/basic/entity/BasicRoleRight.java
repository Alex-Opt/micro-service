package com.ly.mt.core.data.basic.entity;

/**
 * basic_role_right
 *
 * @author taoye
 */
public class BasicRoleRight {
    /**
     * 主键id
     */
    private String id;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 权限id
     */
    private String rightId;
    /**
     * 权限级别
     *
     * @see com.ly.mt.core.base.dict.FuncLevel
     * @see com.ly.mt.core.base.dict.RoleLevel
     * @see com.ly.mt.core.base.dict.AreaLevel
     */
    private String rightLevel;
    /**
     * 权限类型
     *
     * @see com.ly.mt.core.base.dict.RightType
     */
    private String rightType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId;
    }

    public String getRightLevel() {
        return rightLevel;
    }

    public void setRightLevel(String rightLevel) {
        this.rightLevel = rightLevel;
    }

    public String getRightType() {
        return rightType;
    }

    public void setRightType(String rightType) {
        this.rightType = rightType;
    }
}