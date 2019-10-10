package com.ly.mt.center.data.battle.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class OrderBattleExpresses {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("抢单编号")
    private String orders_battle_id;
    @ApiModelProperty("订单编号")
    private String order_id;
    @ApiModelProperty("店铺编号")
    private String shop_id;
    @ApiModelProperty("用户编号")
    private String user_id;
    @ApiModelProperty("快递状态 1-未抢单 3-已被抢单 5-商家已商品校验 7-订单已推送到蜂鸟系统 9-系统拒单/配送异常 11-蜂鸟系统已接单 13-已分配骑手 15-骑手已到店 17-配送中 19-已送达 21-商品签收完成 23-抢单已取消 25-商品被拒签 27-商品申请退货中 29-商品退货退款完成")
    private String state;
    @ApiModelProperty("快递信息")
    private String data;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("更新时间")
    private String modify_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

}