package com.ly.mt.core.common.entity.punchcard;

import com.ly.mt.core.common.entity.base.BaseEntity;

/**
 * 用户积分等级
 * @author  ypmu
 * @date 20190529
 */
public class PunchCardUserPointGrade extends BaseEntity {

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 用户积分
     */
    private String userScore;

    /**
     * 积分等级Id （积分等级配置表）
     */
    private String pointGradeId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserScore() {
        return userScore;
    }

    public void setUserScore(String userScore) {
        this.userScore = userScore;
    }

    public String getPointGradeId() {
        return pointGradeId;
    }

    public void setPointGradeId(String pointGradeId) {
        this.pointGradeId = pointGradeId;
    }
}
