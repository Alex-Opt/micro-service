package com.ly.mt.core.base.entity.marketing;

import java.math.BigDecimal;

/**
 *@Description 收益排行榜
 *@Author  zhuyh
 *//** @deprecated */
public class ShopProfitsTopVo {

    /**
     * 名称
     */
    private String userName;

    /**
     * 收益总额
     */
    private BigDecimal profits;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getProfits() {
        return profits;
    }

    public void setProfits(BigDecimal profits) {
        this.profits = profits;
    }
}
