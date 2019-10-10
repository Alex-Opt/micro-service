package com.ly.mt.center.data.runnerprofit.entity.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description
 *
 *  用户受益总汇dto
 *
 * @author panjingtian
 * @date 2019/6/28 5:01 PM
 */
public class RUserProfitsDto {


    private Long userId;

    private BigDecimal totalProfit;

    private BigDecimal lodeCash;

    private BigDecimal lode;

    private BigDecimal profitFrozen;

    private BigDecimal lodeFrozen;

    private Date createTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public BigDecimal getLodeCash() {
        return lodeCash;
    }

    public void setLodeCash(BigDecimal lodeCash) {
        this.lodeCash = lodeCash;
    }

    public BigDecimal getLode() {
        return lode;
    }

    public void setLode(BigDecimal lode) {
        this.lode = lode;
    }

    public BigDecimal getProfitFrozen() {
        return profitFrozen;
    }

    public void setProfitFrozen(BigDecimal profitFrozen) {
        this.profitFrozen = profitFrozen;
    }

    public BigDecimal getLodeFrozen() {
        return lodeFrozen;
    }

    public void setLodeFrozen(BigDecimal lodeFrozen) {
        this.lodeFrozen = lodeFrozen;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
