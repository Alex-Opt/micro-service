package com.ly.mt.center.data.profits.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
* @Description:    用户赚取日志对象
* @Author:         zhuyh
* @CreateDate:     2019/7/5 21:36
*/
public class ProfitsUserProfitsLogsVO {

    /**
     * id
     */
    private Long id;

    /**
     * 类别
     */
    private Integer profitType;

    /**
     * 朋友名称
     */
    private String friendName;

    /**
     * 收益
     */
    private BigDecimal profit;

    /**
     * 创建时间
     */
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProfitType() {
        return profitType;
    }

    public void setProfitType(Integer profitType) {
        this.profitType = profitType;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
