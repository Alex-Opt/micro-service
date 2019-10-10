package com.ly.mt.core.base.entity.punchcard;

import com.ly.mt.core.base.entity.base.BaseEntity;

/**
 * 打卡关联积分
 * @author ypmu
 * @date 20190529
 *//** @deprecated */
public class PunchCardPoint extends BaseEntity {

    /**
     * 积分规则Id
     */
    private String pointConfigId;

    /**
     * 状态 1：有效，2：无效
      */
    private String status;

    public String getPointConfigId() {
        return pointConfigId;
    }

    public void setPointConfigId(String pointConfigId) {
        this.pointConfigId = pointConfigId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
