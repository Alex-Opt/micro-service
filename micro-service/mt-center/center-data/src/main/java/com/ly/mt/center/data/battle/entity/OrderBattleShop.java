package com.ly.mt.center.data.battle.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class OrderBattleShop {
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
    @ApiModelProperty("小B抢的发货单状态 1-可抢 2-已被别人抢 3-已抢到自己手中 4-释放或者取消抢单")
    private String status;
    @ApiModelProperty("创建时间")
    private String create_time;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}