package com.ly.mt.mis.mis.user.vo;

public class MisUserInfoVo {
    private String id;
    private String name;
    private String loginName;
    private String mobile;
    private String email;
    private String roleNames;
    private String createTime;
    private String lastLoginTime;
    private String validTime;
    private String validUser;


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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleName(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public String getValidUser() {
        return validUser;
    }

    public void setValidUser(String validUser) {
        this.validUser = validUser;
    }
}