package com.ly.mt.core.base.entity.marketing;

import com.ly.mt.core.base.entity.base.BaseEntity;

import java.math.BigDecimal;

/**
 * @创建人 zhuyh
 * @创建时间 2019/6/15
 * @描述
 *//** @deprecated */
public class ShopProfitsLogs extends BaseEntity {

    /**
     * 订单编号（可以为空）
     */
    private Long orderId;

    /**
     * 进货订单编号（可以为空）
     */
    private Long shopPurchasesId;

    /**
     * 获取奖励商家编号
     */
    private Long shopId;

    /**
     * 获取奖励用户编号
     */
    private Long userId;

    /**
     * 订单提供者店铺编号
     */
    private Long providerShopId;

    /**
     * 订单提供者编号
     */
    private Long providerId;

    /**
     * 收益类型 1：邀请，2：通讯录，3：流量，4：抢单奖励，5：抢单金额，6：专属订单，7：提现，8：进货
     */
    private Integer profitType;

    /**
     * 收益金额
     */
    private BigDecimal profit;

    /**
     * 状态 1：待结算，2正常，3：取消
     */
    private Integer status;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getShopPurchasesId() {
        return shopPurchasesId;
    }

    public void setShopPurchasesId(Long shopPurchasesId) {
        this.shopPurchasesId = shopPurchasesId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProviderShopId() {
        return providerShopId;
    }

    public void setProviderShopId(Long providerShopId) {
        this.providerShopId = providerShopId;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Integer getProfitType() {
        return profitType;
    }

    public void setProfitType(Integer profitType) {
        this.profitType = profitType;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
