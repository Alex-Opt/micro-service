package com.ly.mt.core.base.entity.activity;

import com.ly.mt.core.base.entity.base.BaseEntity;

/** @deprecated */
public class ActivityUserGrade extends BaseEntity {

    private String activityId;

    private String limitType;

    private String gradeMinId;

    private String gradeMinName;

    private String gradeMaxId;

    private String gradeMaxName;

    private String userLimitCount;

    private Integer totalCount;

    private String createTime;


    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    public String getGradeMinId() {
        return gradeMinId;
    }

    public void setGradeMinId(String gradeMinId) {
        this.gradeMinId = gradeMinId;
    }

    public String getGradeMinName() {
        return gradeMinName;
    }

    public void setGradeMinName(String gradeMinName) {
        this.gradeMinName = gradeMinName;
    }

    public String getGradeMaxId() {
        return gradeMaxId;
    }

    public void setGradeMaxId(String gradeMaxId) {
        this.gradeMaxId = gradeMaxId;
    }

    public String getGradeMaxName() {
        return gradeMaxName;
    }

    public void setGradeMaxName(String gradeMaxName) {
        this.gradeMaxName = gradeMaxName;
    }

    public String getUserLimitCount() {
        return userLimitCount;
    }

    public void setUserLimitCount(String userLimitCount) {
        this.userLimitCount = userLimitCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}