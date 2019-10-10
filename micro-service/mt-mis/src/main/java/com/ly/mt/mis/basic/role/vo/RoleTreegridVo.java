package com.ly.mt.mis.basic.role.vo;

import com.ly.mt.core.base.eaeyui.Treegrid;

public class RoleTreegridVo extends Treegrid {
    private String parentId;
    private String roleLevel;
    private String projectType;
    private String roleType;
    private String roleTypeName;
    private String createTime;
    private String createUser;
    private String createUserName;
    private String validStatus;
    private String validStatusName;
    private String countUser;


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

    public String getRoleTypeName() {
        return roleTypeName;
    }

    public void setRoleTypeName(String roleTypeName) {
        this.roleTypeName = roleTypeName;
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

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    public String getValidStatusName() {
        return validStatusName;
    }

    public void setValidStatusName(String validStatusName) {
        this.validStatusName = validStatusName;
    }

    public String getCountUser() {
        return countUser;
    }

    public void setCountUser(String countUser) {
        this.countUser = countUser;
    }
}