package com.ly.mt.core.common.entity.punchcard;

import com.ly.mt.core.common.entity.base.BaseEntity;

/**
 * 打卡关联活动优惠
 * @author ypmu
 * @date 20190529
 */
public class PunchCardCoupon extends BaseEntity {

    /**
     * 优惠券信息Id
     */
    private String couponId;

    /**
     * 状态 1：有效，2：无效
     */
    private String status;

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
