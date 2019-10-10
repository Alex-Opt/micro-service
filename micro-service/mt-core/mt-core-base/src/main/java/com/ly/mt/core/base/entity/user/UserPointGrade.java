package com.ly.mt.core.base.entity.user;

import com.ly.mt.core.base.entity.base.BaseEntity;
/** @deprecated */
public class UserPointGrade extends BaseEntity {

    private String userId;

    private String userScore;

    private String pointGradeId;

    private String createTime;

    private String modifyTime;


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

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getModifyTime() {
        return modifyTime;
    }

    @Override
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}