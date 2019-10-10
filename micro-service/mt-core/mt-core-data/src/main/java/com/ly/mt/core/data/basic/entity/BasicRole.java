package com.ly.mt.core.data.basic.entity;

/**
 * basic_role
 *
 * @author taoye
 */
public class BasicRole {
    /**
     * 主键自增id
     */
    private String id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 父级角色id
     */
    private String parentId;
    /**
     * 角色级别
     *
     * @see com.ly.mt.core.base.dict.RoleLevel
     * @see com.ly.mt.core.base.dict.RoleLevelGzgBD
     */
    private String roleLevel;
    /**
     * 角色所属项目
     *
     * @see com.ly.mt.core.base.dict.ProjectType
     */
    private String projectType;
    /**
     * 角色类型
     *
     * @see com.ly.mt.core.base.dict.RoleType
     */
    private String roleType;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 创建人id
     */
    private String createUser;
    /**
     * 修改时间
     */
    private String updateTime;
    /**
     * 修改人id
     */
    private String updateUser;
    /**
     * 有效状态
     *
     * @see com.ly.mt.core.base.dict.ValidStatus
     */
    private String validStatus;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }
}