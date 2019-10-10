package com.ly.mt.core.base.entity.marketing;

import java.math.BigDecimal;
import java.util.Date;

/**
 *@Description  收益日志周期对象
 *@Author  zhuyh
 *//** @deprecated */
public class ShopProfitsLogsCycleVo {

    private Date date;

    private BigDecimal profit;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }
}
