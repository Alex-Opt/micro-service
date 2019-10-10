package com.ly.mt.core.base.entity.marketing;

import com.ly.mt.core.base.entity.base.BaseEntity;

import java.math.BigDecimal;

/**
 * @创建人 zhuyh
 * @创建时间 2019/6/14
 * @描述
 *//** @deprecated */
public class ShopProfits extends BaseEntity {
    /**
     * 商家编号
     */
    private Long shopId;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 累计收益金额
     */
    private BigDecimal totalProfit;

    /**
     * 累计抢单提现汇总金额
     */
    private BigDecimal grabCash;
    /**
     * 淘金金额提现汇总金额
     */
    private BigDecimal lodeCash;
    /**
     * 专属订单提现汇总金额
     */
    private BigDecimal shopOrderCash;
    /**
     * 抢单奖励提现汇总金额
     */
    private BigDecimal withdrawCash;
    /**
     * 抢单奖励金额
     */
    private BigDecimal reward;
    /**
     * 累计抢单成交
     */
    private BigDecimal grab;
    /**
     * 淘金金额
     */
    private BigDecimal lode;
    /**
     * 专属订单金额
     */
    private BigDecimal shopOrder;
    /**
     * 累计冻结收益金额
     */
    private BigDecimal profitFrozen;
    /**
     * 冻结抢单奖励金额
     */
    private BigDecimal rewardFrozen;
    /**
     * 冻结累计抢单成交
     */
    private BigDecimal grabFrozen;
    /**
     * 冻结淘金金额
     */
    private BigDecimal lodeFrozen;
    /**
     * 冻结专属订单金额
     */
    private BigDecimal shopOrderFrozen;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public BigDecimal getGrabCash() {
        return grabCash;
    }

    public void setGrabCash(BigDecimal grabCash) {
        this.grabCash = grabCash;
    }

    public BigDecimal getLodeCash() {
        return lodeCash;
    }

    public void setLodeCash(BigDecimal lodeCash) {
        this.lodeCash = lodeCash;
    }

    public BigDecimal getShopOrderCash() {
        return shopOrderCash;
    }

    public void setShopOrderCash(BigDecimal shopOrderCash) {
        this.shopOrderCash = shopOrderCash;
    }

    public BigDecimal getWithdrawCash() {
        return withdrawCash;
    }

    public void setWithdrawCash(BigDecimal withdrawCash) {
        this.withdrawCash = withdrawCash;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public BigDecimal getGrab() {
        return grab;
    }

    public void setGrab(BigDecimal grab) {
        this.grab = grab;
    }

    public BigDecimal getLode() {
        return lode;
    }

    public void setLode(BigDecimal lode) {
        this.lode = lode;
    }

    public BigDecimal getShopOrder() {
        return shopOrder;
    }

    public void setShopOrder(BigDecimal shopOrder) {
        this.shopOrder = shopOrder;
    }

    public BigDecimal getProfitFrozen() {
        return profitFrozen;
    }

    public void setProfitFrozen(BigDecimal profitFrozen) {
        this.profitFrozen = profitFrozen;
    }

    public BigDecimal getRewardFrozen() {
        return rewardFrozen;
    }

    public void setRewardFrozen(BigDecimal rewardFrozen) {
        this.rewardFrozen = rewardFrozen;
    }

    public BigDecimal getGrabFrozen() {
        return grabFrozen;
    }

    public void setGrabFrozen(BigDecimal grabFrozen) {
        this.grabFrozen = grabFrozen;
    }

    public BigDecimal getLodeFrozen() {
        return lodeFrozen;
    }

    public void setLodeFrozen(BigDecimal lodeFrozen) {
        this.lodeFrozen = lodeFrozen;
    }

    public BigDecimal getShopOrderFrozen() {
        return shopOrderFrozen;
    }

    public void setShopOrderFrozen(BigDecimal shopOrderFrozen) {
        this.shopOrderFrozen = shopOrderFrozen;
    }
}
