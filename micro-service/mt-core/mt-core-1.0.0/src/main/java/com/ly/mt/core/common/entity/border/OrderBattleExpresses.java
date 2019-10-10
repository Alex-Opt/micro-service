package com.ly.mt.core.common.entity.border;

/**
 * 订单快递信息
 * @author zhanglifeng
 * @date 2019-06-19
 */
public class OrderBattleExpresses {
    private String id;

    private String ordersBattleId;

    private String orderId;

    private String shopId;

    private String userId;

    private String state;

    private String createTime;

    private String modifyTime;

    private String data;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }
}