package com.ly.mt.center.data.runnerprofit.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RUserProfits implements Serializable {
    private Long id;

    private Long userId;

    /**
     * 累计受益金额
     * 累计收益金额是所有的金额，包括未结算的
     */
    private BigDecimal totalProfit;


    /**
     * 提现总计金额
     */
    private BigDecimal lodeCash;

    /**
     * 累计赚钱金额
     * 累计赚钱是不包括未结算金额的
     */
    private BigDecimal lode;


    /**
     * 累计冻结金额
     */
    private BigDecimal profitFrozen;

    private BigDecimal lodeFrozen;

    private Date createTime;

    private Date modifyTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", totalProfit=").append(totalProfit);
        sb.append(", lodeCash=").append(lodeCash);
        sb.append(", lode=").append(lode);
        sb.append(", profitFrozen=").append(profitFrozen);
        sb.append(", lodeFrozen=").append(lodeFrozen);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}