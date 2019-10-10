package com.ly.mt.core.base.entity.activity;

import com.ly.mt.core.base.entity.base.BaseEntity;

/** @deprecated */
public class ActivityUserGradeDetail extends BaseEntity {

    private String activityId;

    private String gradeId;

    private String gradeName;

    private String spuId;

    private String spuNum;

    private String createTime;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getSpuNum() {
        return spuNum;
    }

    public void setSpuNum(String spuNum) {
        this.spuNum = spuNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}