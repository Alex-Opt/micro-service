package com.ly.mt.center.data.battle.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class OrderBattleCheckLogs {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("商家编号")
    private String shop_id;
    @ApiModelProperty("抢单编号")
    private String orders_battle_id;
    @ApiModelProperty("订单编号")
    private String order_id;
    @ApiModelProperty("商品SKU")
    private String sku_id;
    @ApiModelProperty("商品条码")
    private String barcode;
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

    public String getOrders_battle_id() {
        return orders_battle_id;
    }

    public void setOrders_battle_id(String orders_battle_id) {
        this.orders_battle_id = orders_battle_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}