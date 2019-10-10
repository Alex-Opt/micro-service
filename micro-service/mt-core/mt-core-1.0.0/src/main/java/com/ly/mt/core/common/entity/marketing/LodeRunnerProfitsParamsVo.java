package com.ly.mt.core.common.entity.marketing;

import java.math.BigDecimal;
import java.util.List;

/**
 * @创建人 zhuyh
 * @创建时间 2019/6/20
 * @描述
 */
public class LodeRunnerProfitsParamsVo {
    private Long userId;

    private List<Long> shopIds;

    private BigDecimal profits;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getShopIds() {
        return shopIds;
    }

    public void setShopIds(List<Long> shopIds) {
        this.shopIds = shopIds;
    }

    public BigDecimal getProfits() {
        return profits;
    }

    public void setProfits(BigDecimal profits) {
        this.profits = profits;
    }
}
