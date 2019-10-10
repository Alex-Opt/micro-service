package com.ly.mt.cabinet.b.replenish.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 商品名称信息
 */
@ApiModel(value="补货订单列表")
public class CabinetReplenishListVo implements Serializable {
    /**
     * 补货订单id
     */
    @ApiModelProperty(value = "补货订单Id", required = true)
    private String id;
    /**
     * skuId
     */
    @ApiModelProperty(value = "skuId", required = true)
    private String skuId;
    /**
     * 购买订单id
     */
    @ApiModelProperty(value = "购买订单id", required = true)
    private String orderId;
    /**
     * 订单明细id
     */
    @ApiModelProperty(value = "订单明细id", required = true)
    private String orderItemId;
    /**
     * 订单完成时间
     */
    @ApiModelProperty(value = "订单完成时间", required = true)
    private String orderFinishTime;

    //格子柜图片
    @ApiModelProperty(value = "格子柜图片", required = true)
    private String bigPicUrl;

    //补货订单状态
    @ApiModelProperty(value = "补货订单状态", required = true)
    private String status;

    //格子柜编号
    @ApiModelProperty(value = "格子柜编号", required = true)
    private String cabinetCode;

    //格子柜舱门
    @ApiModelProperty(value = "格子柜舱门", required = true)
    private String cabinetNo;

    //店铺名称
    @ApiModelProperty(value = "店铺名称", required = true)
    private String shopName;

    //店铺地址
    @ApiModelProperty(value = "店铺地址", required = true)
    private String address;

    //店铺详细地址
    @ApiModelProperty(value = "店铺详细地址", required = true)
    private String detailAddress;

    //sku名称
    @ApiModelProperty(value = "sku名称", required = true)
    private String skuName;

    //spu名称
    @ApiModelProperty(value = "spu名称", required = true)
    private String spuName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(String orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }

    public String getBigPicUrl() {
        return bigPicUrl;
    }

    public void setBigPicUrl(String bigPicUrl) {
        this.bigPicUrl = bigPicUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCabinetCode() {
        return cabinetCode;
    }

    public void setCabinetCode(String cabinetCode) {
        this.cabinetCode = cabinetCode;
    }

    public String getCabinetNo() {
        return cabinetNo;
    }

    public void setCabinetNo(String cabinetNo) {
        this.cabinetNo = cabinetNo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }
}