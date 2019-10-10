package com.ly.mt.core.data.order.entity;

/**
 * order_battle_check_logs
 *
 * @author taoye
 */
public class OrderBattleCheckLogs {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 商家编号
     */
    private String shopId;
    /**
     * 抢单编号
     */
    private String ordersBattleId;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 商品SKU
     */
    private String skuId;
    /**
     * 商品条码
     */
    private String barcode;
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

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}