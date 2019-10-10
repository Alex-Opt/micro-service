package com.ly.mt.center.data.hd.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class HdShopAttOrder {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("订单单号")
    private String order_id;
    @ApiModelProperty("活动用户id activity_user表")
    private String user_id;
    @ApiModelProperty("隶属活动详情id")
    private String shop_att_detail_id;
    @ApiModelProperty("门店id")
    private String shop_id;
    @ApiModelProperty("总价")
    private String total_price;
    @ApiModelProperty("订单状态(FINISH完结，CANCEL取消,UNPAY待支付，OKPAY已支付，SEND已发货)")
    private String order_status;
    @ApiModelProperty("收货地址(程序控制字数限制)")
    private String shipping_address;
    @ApiModelProperty("用户收货地址表主键")
    private String user_address_id;
    @ApiModelProperty("取货码")
    private String get_goods_code;
    @ApiModelProperty("订单创建时间")
    private String create_time;
    @ApiModelProperty("订单更新时间")
    private String update_time;
    @ApiModelProperty("订单类型，预留字段，在线支付，线下交流等")
    private String order_type;


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

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getUser_address_id() {
        return user_address_id;
    }

    public void setUser_address_id(String user_address_id) {
        this.user_address_id = user_address_id;
    }

    public String getGet_goods_code() {
        return get_goods_code;
    }

    public void setGet_goods_code(String get_goods_code) {
        this.get_goods_code = get_goods_code;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

}