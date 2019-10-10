package com.ly.mt.core.common.entity.border;


/**
 * 商家订单检货日志
 * @author zhanglifeng
 */
public class OrderBattleCheckLogs {
    private String id;

    private String shopId;

    private String ordersBattleId;

    private String orderId;

    private String skuId;

    private String barcode;

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