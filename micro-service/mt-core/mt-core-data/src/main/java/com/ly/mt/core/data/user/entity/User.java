package com.ly.mt.core.data.user.entity;

/**
 * user
 *
 * @author taoye
 */
public class User {
    /**
     * 主键id
     */
    private String id;
    /**
     * 到家B端APP用户唯一码
     */
    private String clientId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 微信的OpenId
     */
    private String wxOpenId;
    /**
     * QQ的OpenId
     */
    private String qqOpenId;
    /**
     * 登录账号
     */
    private String loginName;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户身份证
     */
    private String idCard;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 省（直辖市）代码
     */
    private String provinceCode;
    /**
     * 省辖市代码
     */
    private String cityCode;
    /**
     * 县区代码
     */
    private String districtCode;
    /**
     * 用户类型
     *
     * @see com.ly.mt.core.base.dict.UserType
     */
    private String userType;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 店铺id
     */
    private String shopId;
    /**
     * 审核人Id
     */
    private String auditor;
    /**
     * 审核备注
     */
    private String auditRemark;
    /**
     * 注册来源
     *
     * @see com.ly.mt.core.base.dict.QuickType
     */
    private String quickType;
    /**
     * 头像url
     */
    private String avatarPicSrc;
    /**
     * 通知配置(10支付退款异常通知)
     */
    private String noticeConfig;
    /**
     * 是否首次登陆
     */
    private String firstLogin;
    /**
     * 最后登录时间
     */
    private String lastLoginTime;
    /**
     * 最后登录ip
     */
    private String lastLoginIp;
    /**
     * 项目类型
     *
     * @see com.ly.mt.core.base.dict.ProjectType
     */
    private String projectType;
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
    /**
     * 禁用时间
     */
    private String validTime;
    /**
     * 禁用人id
     */
    private String validUser;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public String getQuickType() {
        return quickType;
    }

    public void setQuickType(String quickType) {
        this.quickType = quickType;
    }

    public String getAvatarPicSrc() {
        return avatarPicSrc;
    }

    public void setAvatarPicSrc(String avatarPicSrc) {
        this.avatarPicSrc = avatarPicSrc;
    }

    public String getNoticeConfig() {
        return noticeConfig;
    }

    public void setNoticeConfig(String noticeConfig) {
        this.noticeConfig = noticeConfig;
    }

    public String getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(String firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
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