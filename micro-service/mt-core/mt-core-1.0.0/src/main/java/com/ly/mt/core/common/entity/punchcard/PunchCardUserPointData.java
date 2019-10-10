package com.ly.mt.core.common.entity.punchcard;

import com.ly.mt.core.common.entity.base.BaseEntity;

/**
 * 用户积分流水
 * @author  ypmu
 * @date  20190529
 */
public class PunchCardUserPointData extends BaseEntity {

    /**
     * 用户Id
     */
    String userId;

    /**
     * 积分规则Id
     */
    String pointConfigId;

    /**
     * 积分值
     */
    String score;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPointConfigId() {
        return pointConfigId;
    }

    public void setPointConfigId(String pointConfigId) {
        this.pointConfigId = pointConfigId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
