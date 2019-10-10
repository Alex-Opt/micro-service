package com.ly.mt.core.base.entity.marketing;

import java.math.BigDecimal;

/**
 *@Description 收益详情对象
 *@Author  zhuyh
 *//** @deprecated */
public class ShopProfitsDetailsVO {

    /**
     * 总收益
     */
    private BigDecimal totalProfits;

    /**
     * 提现汇总收益
     */
    private BigDecimal totalCashProfits;

    /**
     * 待结算收益
     */
    private BigDecimal frozenProfits;

    /**
     * 可提现收益
     */
    private BigDecimal cashProfits;


    public BigDecimal getTotalProfits() {
        return totalProfits;
    }

    public void setTotalProfits(BigDecimal totalProfits) {
        this.totalProfits = totalProfits;
    }

    public BigDecimal getTotalCashProfits() {
        return totalCashProfits;
    }

    public void setTotalCashProfits(BigDecimal totalCashProfits) {
        this.totalCashProfits = totalCashProfits;
    }

    public BigDecimal getFrozenProfits() {
        return frozenProfits;
    }

    public void setFrozenProfits(BigDecimal frozenProfits) {
        this.frozenProfits = frozenProfits;
    }

    public BigDecimal getCashProfits() {
        return cashProfits;
    }

    public void setCashProfits(BigDecimal cashProfits) {
        this.cashProfits = cashProfits;
    }
}
