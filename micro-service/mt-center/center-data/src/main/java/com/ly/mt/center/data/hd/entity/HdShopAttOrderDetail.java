package com.ly.mt.center.data.hd.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class HdShopAttOrderDetail {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("门店活动订单id")
    private String order_id;
    @ApiModelProperty("用户id")
    private String user_id;
    @ApiModelProperty("隶属活动id")
    private String shop_att_detail_id;
    @ApiModelProperty("商品skuid")
    private String goods_sku_id;
    @ApiModelProperty("商品spuid")
    private String goods_spu_id;
    @ApiModelProperty("购买商品数量")
    private String goods_quantity;
    @ApiModelProperty("商品单价(goods_info中的零售价)")
    private String goods_price;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("更新时间")
    private String uodate_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getShop_att_detail_id() {
        return shop_att_detail_id;
    }

    public void setShop_att_detail_id(String shop_att_detail_id) {
        this.shop_att_detail_id = shop_att_detail_id;
    }

    public String getGoods_sku_id() {
        return goods_sku_id;
    }

    public void setGoods_sku_id(String goods_sku_id) {
        this.goods_sku_id = goods_sku_id;
    }

    public String getGoods_spu_id() {
        return goods_spu_id;
    }

    public void setGoods_spu_id(String goods_spu_id) {
        this.goods_spu_id = goods_spu_id;
    }

    public String getGoods_quantity() {
        return goods_quantity;
    }

    public void setGoods_quantity(String goods_quantity) {
        this.goods_quantity = goods_quantity;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUodate_time() {
        return uodate_time;
    }

    public void setUodate_time(String uodate_time) {
        this.uodate_time = uodate_time;
    }

}