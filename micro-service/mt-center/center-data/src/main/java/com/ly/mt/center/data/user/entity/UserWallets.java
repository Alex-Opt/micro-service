package com.ly.mt.center.data.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserWallets {
    @ApiModelProperty("用户编号")
    private String user_id;
    @ApiModelProperty("当前金额")
    private String amount;
    @ApiModelProperty("冻结金额")
    private String freeze_amount;
    @ApiModelProperty("总金额")
    private String total_amount;
    @ApiModelProperty("当前淘金金额")
    private String lode_runner_amount;
    @ApiModelProperty("个人赚钱金额")
    private String personal_amount;
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

    public String getFreeze_amount() {
        return freeze_amount;
    }

    public void setFreeze_amount(String freeze_amount) {
        this.freeze_amount = freeze_amount;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getLode_runner_amount() {
        return lode_runner_amount;
    }

    public void setLode_runner_amount(String lode_runner_amount) {
        this.lode_runner_amount = lode_runner_amount;
    }

    public String getPersonal_amount() {
        return personal_amount;
    }

    public void setPersonal_amount(String personal_amount) {
        this.personal_amount = personal_amount;
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