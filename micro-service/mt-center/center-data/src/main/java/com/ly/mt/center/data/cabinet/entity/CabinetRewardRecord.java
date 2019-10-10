package com.ly.mt.center.data.cabinet.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 奖励明细记录
 */
@ApiModel
public class CabinetRewardRecord {

    @ApiModelProperty(value = "主键id", required = true)
    private String id;

    @ApiModelProperty(value = "买家id")
    private String buyer_id;

    @ApiModelProperty(value = "店铺id")
    private String store_id;

    @ApiModelProperty(value = "店铺所有人id")
    private String owner_user_id;

    @ApiModelProperty(value = "订单id", required = true)
    private String order_id;

    @ApiModelProperty(value = "订单原始金额", required = true)
    private String order_old_money;

    @ApiModelProperty(value = "分成比例", required = true)
    private String divide_scale;

    @ApiModelProperty(value = "奖励金额", required = true)
    private String reward;

    @ApiModelProperty(value = "类型", required = true)
    private String type;

    @ApiModelProperty(value = "状态", required = true)
    private String status;

    @ApiModelProperty(value = "创建时间")
    private String create_time;

    @ApiModelProperty(value = "结算时间")
    private String update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getOwner_user_id() {
        return owner_user_id;
    }

    public void setOwner_user_id(String owner_user_id) {
        this.owner_user_id = owner_user_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_old_money() {
        return order_old_money;
    }

    public void setOrder_old_money(String order_old_money) {
        this.order_old_money = order_old_money;
    }

    public String getDivide_scale() {
        return divide_scale;
    }

    public void setDivide_scale(String divide_scale) {
        this.divide_scale = divide_scale;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
