package com.ly.mt.center.data.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserProfits {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("用户编号")
    private String user_id;
    @ApiModelProperty("累计收益金额")
    private String total_profit;
    @ApiModelProperty("累计赚钱金额提现汇总金额")
    private String lode_cash;
    @ApiModelProperty("累计赚钱金额")
    private String lode;
    @ApiModelProperty("累计冻结赚钱金额")
    private String lode_frozen;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("修改时间")
    private String modify_time;


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

    public String getTotal_profit() {
        return total_profit;
    }

    public void setTotal_profit(String total_profit) {
        this.total_profit = total_profit;
    }

    public String getLode_cash() {
        return lode_cash;
    }

    public void setLode_cash(String lode_cash) {
        this.lode_cash = lode_cash;
    }

    public String getLode() {
        return lode;
    }

    public void setLode(String lode) {
        this.lode = lode;
    }

    public String getLode_frozen() {
        return lode_frozen;
    }

    public void setLode_frozen(String lode_frozen) {
        this.lode_frozen = lode_frozen;
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