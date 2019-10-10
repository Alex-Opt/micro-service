package com.ly.mt.core.common.entity.activity;

/**
 * @author zhanglifeng
 * @description 促销活动模型-封装传递到前端重要参数的模型，不需要extends BaseEntity
 * @date 2019-05-23
 */
public class ActivityModel {
    /**
     * 促销活动主键id
     */
    private String activityId;

    /**
     * 促销优惠活动名称
     */
    private String activityName;

    /**
     * 促销优惠金额
     */
    private String denomination;

    /**
     * 促销优惠折扣，和优惠活动金额是互斥的
     */
    private String discountRate;

    /**
     * 优惠开始时间
     */
    private String startTime;

    /**
     * 优惠结束时间
     */
    private String endTime;


    public ActivityModel() {
    }

    /**
     * 构造方法
     * @param activityId
     * @param activityName
     * @param denomination
     * @param discountRate
     * @param startTime
     * @param endTime
     */
    public ActivityModel(String activityId, String activityName, String denomination, String discountRate, String startTime, String endTime) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.denomination = denomination;
        this.discountRate = discountRate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
