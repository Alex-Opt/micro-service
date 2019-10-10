package com.ly.mt.core.base.entity.marketing;

import java.math.BigDecimal;

/**
 *@Description 总收益对象
 *@Author  zhuyh
 *//** @deprecated */
public class ShopProfitsSumVo {
    /**
     * 总收益
     */
    private BigDecimal totalProfit;

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


    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
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
}
