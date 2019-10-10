package com.ly.mt.center.data.profits.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProfitsUserRankVo {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatarPicSrc;

    /**
     * 赚钱额度
     */
    private BigDecimal profits;

    /**
     * 查询日期
     */
    private Date searchDate;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatarPicSrc() {
        return userAvatarPicSrc;
    }

    public void setUserAvatarPicSrc(String userAvatarPicSrc) {
        this.userAvatarPicSrc = userAvatarPicSrc;
    }

    public BigDecimal getProfits() {
        return profits;
    }

    public void setProfits(BigDecimal profits) {
        this.profits = profits;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }
}
