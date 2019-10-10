package com.ly.mt.center.data.runnerprofit.entity.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description
 *
 * 受益日志dto
 *
 * @author panjingtian
 * @date 2019/6/28 5:01 PM
 */
public class RUserProfitLogsDto {


    private Long orderId;

    private Long userId;

    private Long providerId;

    private Byte profitType;

    /**
     * 受益的金额
     */
    private BigDecimal profit;

    private Byte status;

    private Date createTime;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Byte getProfitType() {
        return profitType;
    }

    public void setProfitType(Byte profitType) {
        this.profitType = profitType;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
