package com.ly.mt.mis.gzg.user.vo;

/**
 * MOTI售卖柜-用户管理-用户列表
 *
 * @author taoye
 */
public class GzgUserDatagridVo {
    /**
     * 用户编号
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
     * 所属地区
     */
    private String areaName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 创建人ID
     */
    private String createUser;
    /**
     * 创建人姓名
     */
    private String createUserName;
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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }
}