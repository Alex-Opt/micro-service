package com.ly.mt.core.base.entity.punchcard;

import com.ly.mt.core.base.entity.base.BaseEntity;

/**
 * 积分规则配置
 *
 * @author ypmu
 * @date 20190529
 *//** @deprecated */
public class PunchCardPointConfig extends BaseEntity {

    /**
     * 规则名称
     */
    private String title;
    /**
     * 规则标识名
     */
    private String name;
    /**
     * 积分值
     */
    private String score;
    /**
     * 规则类型
     */
    private String pointType;
    /**
     * 规则状态 1：上架，2：下架
     */
    private String pointStatus;
    /**
     * 有效期开始时间
     */
    private String startTime;
    /**
     * 有效期结束时间
     */
    private String endTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPointType() {
        return pointType;
    }

    public void setPointType(String pointType) {
        this.pointType = pointType;
    }

    public String getPointStatus() {
        return pointStatus;
    }

    public void setPointStatus(String pointStatus) {
        this.pointStatus = pointStatus;
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
