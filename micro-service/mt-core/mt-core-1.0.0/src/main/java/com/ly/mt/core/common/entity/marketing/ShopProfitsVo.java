package com.ly.mt.core.common.entity.marketing;

/**
 *@Description 收益对象
 *@Author  zhuyh
 */
public class ShopProfitsVo extends ShopProfits {
    /**
     * 类型：grab：抢单成交额、reward：奖励成交额
     */
    private String tp;
    /**
     * 排行榜值
     */
    private Integer topValue;

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public Integer getTopValue() {
        return topValue;
    }

    public void setTopValue(Integer topValue) {
        this.topValue = topValue;
    }
}
