package com.ly.mt.core.data.activity.entity;

/**
 * activity_security_code
 *
 * @author taoye
 */
public class ActivitySecurityCode {
    /**
     * 主键id
     */
    private String id;
    /**
     * 防伪码
     */
    private String securityCode;
    /**
     * 红包金额
     */
    private String money;
    /**
     * 环境
     */
    private String profile;
    /**
     * 创建时间
     */
    private String createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}