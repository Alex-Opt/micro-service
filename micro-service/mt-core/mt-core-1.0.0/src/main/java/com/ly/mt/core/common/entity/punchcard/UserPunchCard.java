package com.ly.mt.core.common.entity.punchcard;

import com.ly.mt.core.common.entity.base.BaseEntity;

/**
 * 用户打卡记录表
 * @author  ypmu
 * @date  20190529
 */
public class UserPunchCard extends BaseEntity {

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 打卡时间
     */
    private String punchTime;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(String punchTime) {
        this.punchTime = punchTime;
    }
}
