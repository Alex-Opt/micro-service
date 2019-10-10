package com.ly.mt.core.data.order.entity;

/**
 * order_battle_shop
 *
 * @author taoye
 */
public class OrderBattleShop {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 抢单编号
     */
    private String ordersBattleId;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 店铺编号
     */
    private String shopId;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 小B抢的发货单状态
     */
    private String status;
    /**
     * 创建时间
     */
    private String createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrdersBattleId() {
        return ordersBattleId;
    }

    public void setOrdersBattleId(String ordersBattleId) {
        this.ordersBattleId = ordersBattleId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}