package com.ly.mt.core.base.entity.hd.model;

import java.math.BigDecimal;

/**
 * @description
 * 带商品信息的订单详情model
 * @author panjingtian
 * @date 2019/6/17 7:52 PM
 *//** @deprecated */
public class HdShopOrderDetailGoodsModel implements Cloneable {


    private String orderId;

    private Long userId;

    private Long shopAttDetailId;

    private Long goodsSkuId;

    private Long goodsSpuId;

    private Integer goodsQuantity;

    private BigDecimal goodsPrice;

    private String goodsSpuName;

    private String goodsSkuName;

    /**
     * 商品图片
     */
    private String picduceUrl;


    public HdShopOrderDetailGoodsModel() {
    }

    public String getPicduceUrl() {
        return picduceUrl;
    }

    public void setPicduceUrl(String picduceUrl) {
        this.picduceUrl = picduceUrl;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShopAttDetailId() {
        return shopAttDetailId;
    }

    public void setShopAttDetailId(Long shopAttDetailId) {
        this.shopAttDetailId = shopAttDetailId;
    }

    public Long getGoodsSkuId() {
        return goodsSkuId;
    }

    public void setGoodsSkuId(Long goodsSkuId) {
        this.goodsSkuId = goodsSkuId;
    }

    public Long getGoodsSpuId() {
        return goodsSpuId;
    }

    public void setGoodsSpuId(Long goodsSpuId) {
        this.goodsSpuId = goodsSpuId;
    }

    public Integer getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(Integer goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsSpuName() {
        return goodsSpuName;
    }

    public void setGoodsSpuName(String goodsSpuName) {
        this.goodsSpuName = goodsSpuName;
    }

    public String getGoodsSkuName() {
        return goodsSkuName;
    }

    public void setGoodsSkuName(String goodsSkuName) {
        this.goodsSkuName = goodsSkuName;
    }

    @Override
    protected HdShopOrderDetailGoodsModel clone() throws CloneNotSupportedException {

        HdShopOrderDetailGoodsModel hdShopOrderDetailGoodsModel = (HdShopOrderDetailGoodsModel) super.clone();
        return hdShopOrderDetailGoodsModel;
    }
}
