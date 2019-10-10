package com.ly.mt.core.common.entity.border;

/**
 * @author zhanglifeng
 * 抢单日志
 */
public class OrderBattleLogs {
    private String id;

    private String ordersBattleId;

    private String orderId;

    private String userId;

    private String shopId;

    private String userType;

    private String status;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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