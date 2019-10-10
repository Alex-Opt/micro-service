package com.ly.mt.blue.tooth.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description user
 * @Author whl
 */
@ApiModel(value="用户信息对象")
public class BlueToothUserVo {
    @ApiModelProperty(value = "用户id", required = true)
    private String id;
    /**
     * 十六进制userId
     */
    @ApiModelProperty(value = "十六进制userId", required = true)
    private String hexadecimalUserId;
    /**
     * @Description 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;
    /**
     * @Description 电话
     */
    @ApiModelProperty(value = "电话", required = true)
    private String mobile;
    /**
     * @Description 邮箱
     */
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;
    /**
     * @Description 登录账号
     */
    @ApiModelProperty(value = "登录账号", required = true)
    private String loginName;
    /**
     * @Description 性别
     */
    @ApiModelProperty(value = "性别", required = true)
    private String sex;
    /**
     * @Description 生日
     */
    @ApiModelProperty(value = "生日", required = true)
    private String birthday;
    /**
     * @Description 用户昵称
     */
    @ApiModelProperty(value = "用户昵称", required = true)
    private String nickName;

    /**
     * @Description 头像url
     */
    @ApiModelProperty(value = "头像url", required = true)
    private String avatarPicSrc;

    /**
     * @Description 戒烟目标(每天不超过)
     */
    @ApiModelProperty(value = "戒烟目标(每天不超过)", required = true)
    private int smokingTarget;
    /**
     * @Description 达标天数(健康抽烟)
     */
    @ApiModelProperty(value = "达标天数(健康抽烟)", required = true)
    private int complianceDaysTarget;
    /**
     * @Description 日均抽烟
     */
    @ApiModelProperty(value = "日均抽烟", required = true)
    private int avgSmoking;
    /**
     * @Description 用户达标天数
     */
    @ApiModelProperty(value = "用户达标天数", required = true)
    private int userComplianceDays;
    /**
     * @Description 累计节省
     */
    @ApiModelProperty(value = "累计节省", required = true)
    private int totalMoney;

    /**
     * @Description
     */
    @ApiModelProperty(value = "认证状态", required = true)
    private String certificationStatus;

    /**
     * @Description
     */
    @ApiModelProperty(value = "是否首次登陆 0:是 1:否", required = true)
    private String firstLogin;

    /**
     * @Description
     */
    @ApiModelProperty(value = "报修状态 0:已填写 2:待审核 3:未申请", required = true)
    private String repairsStatus;

    /**
     * @Description
     */
    @ApiModelProperty(value = "报修Id", required = true)
    private String repairsId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarPicSrc() {
        return avatarPicSrc;
    }

    public void setAvatarPicSrc(String avatarPicSrc) {
        this.avatarPicSrc = avatarPicSrc;
    }

    public int getSmokingTarget() {
        return smokingTarget;
    }

    public void setSmokingTarget(int smokingTarget) {
        this.smokingTarget = smokingTarget;
    }

    public int getComplianceDaysTarget() {
        return complianceDaysTarget;
    }

    public void setComplianceDaysTarget(int complianceDaysTarget) {
        this.complianceDaysTarget = complianceDaysTarget;
    }

    public int getAvgSmoking() {
        return avgSmoking;
    }

    public void setAvgSmoking(int avgSmoking) {
        this.avgSmoking = avgSmoking;
    }

    public int getUserComplianceDays() {
        return userComplianceDays;
    }

    public void setUserComplianceDays(int userComplianceDays) {
        this.userComplianceDays = userComplianceDays;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getHexadecimalUserId() {
        return hexadecimalUserId;
    }

    public void setHexadecimalUserId(String hexadecimalUserId) {
        this.hexadecimalUserId = hexadecimalUserId;
    }

    public String getCertificationStatus() {
        return certificationStatus;
    }

    public void setCertificationStatus(String certificationStatus) {
        this.certificationStatus = certificationStatus;
    }


    public String getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(String firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getRepairsStatus() {
        return repairsStatus;
    }

    public void setRepairsStatus(String repairsStatus) {
        this.repairsStatus = repairsStatus;
    }

    public String getRepairsId() {
        return repairsId;
    }

    public void setRepairsId(String repairsId) {
        this.repairsId = repairsId;
    }
}