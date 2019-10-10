package com.ly.mt.cabinet.c.user.entity;


import com.ly.mt.cabinet.c.coupon.entity.GzgCouponCode;

import java.util.List;

/**
 * @Description 用户登录vo类
 * @Author gongjy
 */

public class GzgUserVo {
    private String id;
    private String mobile;
    private String dynamicCode;
    private String userStatus;
    private String token;
    private String createTime;
    private List<GzgCouponCode> gzgCouponCodeVoList;


    /**
     * 是否领取过优惠卷
     */
    private Boolean isCouponReceived;


    public Boolean getCouponReceived() {
        return isCouponReceived;
    }

    public void setCouponReceived(Boolean couponReceived) {
        isCouponReceived = couponReceived;
    }
    public GzgUserVo() {
    }

    public GzgUserVo(String id, String mobile, String dynamicCode, String userStatus, String token, String createTime, List<GzgCouponCode> gzgCouponCodeVoList) {
        this.id = id;
        this.mobile = mobile;
        this.dynamicCode = dynamicCode;
        this.userStatus = userStatus;
        this.token = token;
        this.createTime = createTime;
        this.gzgCouponCodeVoList = gzgCouponCodeVoList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDynamicCode() {
        return dynamicCode;
    }

    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<GzgCouponCode> getGzgCouponCodeVoList() {
        return gzgCouponCodeVoList;
    }

    public void setGzgCouponCodeVoList(List<GzgCouponCode> gzgCouponCodeVoList) {
        this.gzgCouponCodeVoList = gzgCouponCodeVoList;
    }

    @Override
    public String toString() {
        return "GzgUserVo{" +
                "id='" + id + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dynamicCode='" + dynamicCode + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", token='" + token + '\'' +
                ", createTime='" + createTime + '\'' +
                ", gzgCouponCodeVoList=" + gzgCouponCodeVoList +
                '}';
    }
}