package com.ly.mt.center.data.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserMakeMoneyMonths {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("用户编号")
    private String user_id;
    @ApiModelProperty("本月收益")
    private String month_profit;
    @ApiModelProperty("本月好友消费")
    private String friend_amount;
    @ApiModelProperty("本月好友订单")
    private String friend_order;
    @ApiModelProperty("本月邀请人数")
    private String invite;
    @ApiModelProperty("创建时间")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMonth_profit() {
        return month_profit;
    }

    public void setMonth_profit(String month_profit) {
        this.month_profit = month_profit;
    }

    public String getFriend_amount() {
        return friend_amount;
    }

    public void setFriend_amount(String friend_amount) {
        this.friend_amount = friend_amount;
    }

    public String getFriend_order() {
        return friend_order;
    }

    public void setFriend_order(String friend_order) {
        this.friend_order = friend_order;
    }

    public String getInvite() {
        return invite;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}