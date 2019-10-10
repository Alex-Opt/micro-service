package com.ly.mt.core.base.entity.punchcard;

import com.ly.mt.core.base.entity.base.BaseEntity;

/**
 * 优惠券表
 * @author ypmu
 * @date 20190529
 *//** @deprecated */
public class PunchCardCouponInfo extends BaseEntity {

    /**
     * 优惠券名称
     */
    private String couponName;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 优惠金额
     */
    private String denomination;

    /**
     * 优惠折扣
     */
    private String discountRate;

    /**
     * 优惠类型
     */
    private String limitType;

    /**
     * 最小订单金额
     */
    private String startFee;

    /**
     * 描述
     */
    private String remark;

    /**
     * 创建人
     */
    private String createrId;

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
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

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    public String getStartFee() {
        return startFee;
    }

    public void setStartFee(String startFee) {
        this.startFee = startFee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }
}
