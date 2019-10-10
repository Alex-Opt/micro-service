package com.ly.mt.center.data.shop.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShopPurchasesRefundItem {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("商铺编号")
    private String shop_id;
    @ApiModelProperty("退货单编号")
    private String shop_purchases_refund_id;
    @ApiModelProperty("进货订单编号")
    private String shop_purchases_id;
    @ApiModelProperty("商品SKU")
    private String sku_id;
    @ApiModelProperty("退货数量")
    private String refund_num;
    @ApiModelProperty("商品单价")
    private String sku_price;
    @ApiModelProperty("商品订单金额")
    private String sku_order_price;
    @ApiModelProperty("实际退货金额")
    private String sku_refund_price;
    @ApiModelProperty("扣减分摊优惠金额")
    private String subtract_coupon_price;
    @ApiModelProperty("扣减分摊运费金额")
    private String subtract_freight_price;
    @ApiModelProperty("创建时间")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_purchases_refund_id() {
        return shop_purchases_refund_id;
    }

    public void setShop_purchases_refund_id(String shop_purchases_refund_id) {
        this.shop_purchases_refund_id = shop_purchases_refund_id;
    }

    public String getShop_purchases_id() {
        return shop_purchases_id;
    }

    public void setShop_purchases_id(String shop_purchases_id) {
        this.shop_purchases_id = shop_purchases_id;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getRefund_num() {
        return refund_num;
    }

    public void setRefund_num(String refund_num) {
        this.refund_num = refund_num;
    }

    public String getSku_price() {
        return sku_price;
    }

    public void setSku_price(String sku_price) {
        this.sku_price = sku_price;
    }

    public String getSku_order_price() {
        return sku_order_price;
    }

    public void setSku_order_price(String sku_order_price) {
        this.sku_order_price = sku_order_price;
    }

    public String getSku_refund_price() {
        return sku_refund_price;
    }

    public void setSku_refund_price(String sku_refund_price) {
        this.sku_refund_price = sku_refund_price;
    }

    public String getSubtract_coupon_price() {
        return subtract_coupon_price;
    }

    public void setSubtract_coupon_price(String subtract_coupon_price) {
        this.subtract_coupon_price = subtract_coupon_price;
    }

    public String getSubtract_freight_price() {
        return subtract_freight_price;
    }

    public void setSubtract_freight_price(String subtract_freight_price) {
        this.subtract_freight_price = subtract_freight_price;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}