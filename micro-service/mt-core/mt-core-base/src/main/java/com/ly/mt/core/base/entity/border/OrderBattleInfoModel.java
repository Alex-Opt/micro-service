package com.ly.mt.core.base.entity.border;

import com.ly.mt.core.base.entity.trade.TradeOrderItemVo;

import java.util.List;

/** @deprecated */
public class OrderBattleInfoModel {
    /**
     * 购买的商品信息模型的集合
     */
    private List<TradeOrderItemVo> itemList;

    /**
     * 倒计时截至时间
     */
    private String deadLine;

    /**
     * 订单实付金额
     */
    private String orderMoney;

    /**
     * 订单中包含的商品数量
     */
    private String skuTotalNum;

    /**
     * 该笔订单的奖励金
     */
    private String bonus;

    /**
     * 小B距离订单位置的直线距离
     */
    private String distance;

    private String orderId;

    private String orderBattleId;

    /**
     * 收货地址id
     */
    private String addressId;
    /**
     * 小B抢的发货单状态 1-可抢 2-已被别人抢 3-已抢到自己手中 4-释放或者取消抢单
     */
    private String status;

    public List<TradeOrderItemVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<TradeOrderItemVo> itemList) {
        this.itemList = itemList;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getSkuTotalNum() {
        return skuTotalNum;
    }

    public void setSkuTotalNum(String skuTotalNum) {
        this.skuTotalNum = skuTotalNum;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderBattleId() {
        return orderBattleId;
    }

    public void setOrderBattleId(String orderBattleId) {
        this.orderBattleId = orderBattleId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
