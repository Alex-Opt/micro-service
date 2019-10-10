package com.ly.mt.core.common.entity.activity;

import com.ly.mt.core.common.entity.base.BaseEntity;

import java.util.Date;

public class ActivityGoodsDetail extends BaseEntity {

    private String activityId;

    private String spuId;

    private String spuName;

    private String maxSellNum;

    private String createTime;


    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getMaxSellNum() {
        return maxSellNum;
    }

    public void setMaxSellNum(String maxSellNum) {
        this.maxSellNum = maxSellNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}