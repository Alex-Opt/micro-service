package com.ly.mt.core.common.entity.shop;

/**
 * 淘金人信息 返现比例
 */
public class LodeRunnerBo {

    /**
     * 店铺编号
     */
    private String shopId;

    /**
     * 用户编号
     */
    private String userId;


    /**
     * 用户等级
     */
    private String level;

    /**
     * 父节点
     */
    private String fatherId;

    /**
     * 根节点
     */
    private String topUserId;
    /**
     * 返现比例
     */
    private String proportion;


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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getTopUserId() {
        return topUserId;
    }

    public void setTopUserId(String topUserId) {
        this.topUserId = topUserId;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }
}
