package com.ly.mt.core.common.entity.marketing;

import java.math.BigDecimal;
import java.util.Date;

/**
 *@Description 收益日志-奖励订单对象
 *@Author  zhuyh
 */
public class ShopProfitsLogsRewardOrderVo {
    /**
     * 用户名称
     */
    private String userName;

    /**
     * 订单时间
     */
    private Date orderDate;

    /**
     * 抢单金额
     */
    private BigDecimal grab;

    /**
     * 抢单奖励金额
     */
    private BigDecimal reward;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getGrab() {
        return grab;
    }

    public void setGrab(BigDecimal grab) {
        this.grab = grab;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }
}
