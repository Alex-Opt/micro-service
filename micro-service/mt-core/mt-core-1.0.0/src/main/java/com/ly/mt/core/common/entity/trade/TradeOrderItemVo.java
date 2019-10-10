package com.ly.mt.core.common.entity.trade;

import com.ly.mt.core.common.entity.goods.GoodsAttrValue;

import java.util.List;
import java.util.Map;

/**
 * 订单明细表  返回前端用Vo
 *
 * @author zhanglifeng
 * @String 2019-05-21
 */
public class TradeOrderItemVo {
    private String id;

    private String orderId;

    private String spuId;

    private String spuName;

    private String skuId;

    private String skuName;

    private String skuPrice;

    private String skuNum;

    private String pomotionPrice;

    private String freightPrice;

    private String gySpuId;

    private String gySpuCode;

    private String gySkuCode;

    private String paymentPrice;

    private String createTime;

    private String pictureUrl;

    /**
     * sku商品月销量
     */
    private String skuMonthSellerNumber;

    /**
     * sku商品的属性信息封装
     */
    private List<Map> goodsAttrValues;

    public List<Map> getGoodsAttrValues() {
        return goodsAttrValues;
    }

    public void setGoodsAttrValues(List<Map> goodsAttrValues) {
        this.goodsAttrValues = goodsAttrValues;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    public String getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(String skuPrice) {
        this.skuPrice = skuPrice;
    }

    public String getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(String skuNum) {
        this.skuNum = skuNum;
    }

    public String getPomotionPrice() {
        return pomotionPrice;
    }

    public void setPomotionPrice(String pomotionPrice) {
        this.pomotionPrice = pomotionPrice;
    }

    public String getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(String freightPrice) {
        this.freightPrice = freightPrice;
    }

    public String getGySpuId() {
        return gySpuId;
    }

    public void setGySpuId(String gySpuId) {
        this.gySpuId = gySpuId == null ? null : gySpuId.trim();
    }

    public String getGySpuCode() {
        return gySpuCode;
    }

    public void setGySpuCode(String gySpuCode) {
        this.gySpuCode = gySpuCode == null ? null : gySpuCode.trim();
    }

    public String getGySkuCode() {
        return gySkuCode;
    }

    public void setGySkuCode(String gySkuCode) {
        this.gySkuCode = gySkuCode == null ? null : gySkuCode.trim();
    }

    public String getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(String paymentPrice) {
        this.paymentPrice = paymentPrice;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getSkuMonthSellerNumber() {
        return skuMonthSellerNumber;
    }

    public void setSkuMonthSellerNumber(String skuMonthSellerNumber) {
        this.skuMonthSellerNumber = skuMonthSellerNumber;
    }
}