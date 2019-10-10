package com.ly.mt.mis.mis.user.vo;

/**
 * 系统管理-用户管理
 *
 * @author taoye
 */
public class MisUserDatagridVo {
    /**
     * 员工编号
     */
    private String id;
    /**
     * 所属角色
     */
    private String roleNames;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 最后登录时间
     */
    private String lastLoginTime;
    /**
     * 有效状态
     */
    private String validStatus;
    /**
     * 项目类型
     */
    private String projectType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }
}