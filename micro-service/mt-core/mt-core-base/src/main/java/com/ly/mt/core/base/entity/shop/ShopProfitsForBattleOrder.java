package com.ly.mt.core.base.entity.shop;


/**
 * 商家收益汇总
 * @author zhanglifeng
 * @String 2019-06-18
 *//** @deprecated */
public class ShopProfitsForBattleOrder {
    private String id;

    private String shopId;

    private String userId;

    private String totalProfit;

    private String grabCash;

    private String lodeCash;

    private String shopOrderCash;

    private String withdrawCash;

    private String reward;

    private String grab;

    private String lode;

    private String shopOrder;

    private String profitFrozen;

    private String rewardFrozen;

    private String grabFrozen;

    private String lodeFrozen;

    private String shopOrderFrozen;

    private String createTime;

    private String modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getGrabCash() {
        return grabCash;
    }

    public void setGrabCash(String grabCash) {
        this.grabCash = grabCash;
    }

    public String getLodeCash() {
        return lodeCash;
    }

    public void setLodeCash(String lodeCash) {
        this.lodeCash = lodeCash;
    }

    public String getShopOrderCash() {
        return shopOrderCash;
    }

    public void setShopOrderCash(String shopOrderCash) {
        this.shopOrderCash = shopOrderCash;
    }

    public String getWithdrawCash() {
        return withdrawCash;
    }

    public void setWithdrawCash(String withdrawCash) {
        this.withdrawCash = withdrawCash == null ? null : withdrawCash.trim();
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getGrab() {
        return grab;
    }

    public void setGrab(String grab) {
        this.grab = grab;
    }

    public String getLode() {
        return lode;
    }

    public void setLode(String lode) {
        this.lode = lode;
    }

    public String getShopOrder() {
        return shopOrder;
    }

    public void setShopOrder(String shopOrder) {
        this.shopOrder = shopOrder;
    }

    public String getProfitFrozen() {
        return profitFrozen;
    }

    public void setProfitFrozen(String profitFrozen) {
        this.profitFrozen = profitFrozen;
    }

    public String getRewardFrozen() {
        return rewardFrozen;
    }

    public void setRewardFrozen(String rewardFrozen) {
        this.rewardFrozen = rewardFrozen;
    }

    public String getGrabFrozen() {
        return grabFrozen;
    }

    public void setGrabFrozen(String grabFrozen) {
        this.grabFrozen = grabFrozen;
    }

    public String getLodeFrozen() {
        return lodeFrozen;
    }

    public void setLodeFrozen(String lodeFrozen) {
        this.lodeFrozen = lodeFrozen;
    }

    public String getShopOrderFrozen() {
        return shopOrderFrozen;
    }

    public void setShopOrderFrozen(String shopOrderFrozen) {
        this.shopOrderFrozen = shopOrderFrozen;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}