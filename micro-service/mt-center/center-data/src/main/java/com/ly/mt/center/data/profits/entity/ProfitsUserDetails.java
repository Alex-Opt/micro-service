package com.ly.mt.center.data.profits.entity;

import java.math.BigDecimal;

/**
* @Description: 用户赚钱-详情
* @Author:         zhuyh
*/
public class ProfitsUserDetails {

    /**
     * 现金金额
     */
    private BigDecimal totalProfit;
    /**
     * 可体现金额
     */
    private BigDecimal lode;
    /**
     * 累计冻结金额
     */
    private BigDecimal lodeFrozen;


    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public BigDecimal getLode() {
        return lode;
    }

    public void setLode(BigDecimal lode) {
        this.lode = lode;
    }

    public BigDecimal getLodeFrozen() {
        return lodeFrozen;
    }

    public void setLodeFrozen(BigDecimal lodeFrozen) {
        this.lodeFrozen = lodeFrozen;
    }
}
