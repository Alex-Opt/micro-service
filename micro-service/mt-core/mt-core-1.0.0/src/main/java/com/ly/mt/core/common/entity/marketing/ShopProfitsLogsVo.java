package com.ly.mt.core.common.entity.marketing;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @创建人 zhuyh
 * @创建时间 2019/6/17
 * @描述
 */
public class ShopProfitsLogsVo {
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 类型
     */
    private Integer tp;

    /**
     * 符号
     */
    private String symbol;

    /**
     * 类型名称
     */
    private String tpName;

    /**
     * 值
     */
    private BigDecimal profit;

    /**
     * 创建时间
     */
    private Date createTime;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getTp() {
        return tp;
    }

    public void setTp(Integer tp) {
        this.tp = tp;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTpName() {
        return tpName;
    }

    public void setTpName(String tpName) {
        this.tpName = tpName;
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
