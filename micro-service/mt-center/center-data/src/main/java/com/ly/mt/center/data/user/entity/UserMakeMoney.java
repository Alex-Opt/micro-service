package com.ly.mt.center.data.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserMakeMoney {
    @ApiModelProperty("用户编号")
    private String user_id;
    @ApiModelProperty("金额")
    private String amount;
    @ApiModelProperty("上升/下降名次")
    private String ranking;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("更新时间")
    private String modify_time;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
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