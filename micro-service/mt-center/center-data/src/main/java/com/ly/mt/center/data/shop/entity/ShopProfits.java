package com.ly.mt.center.data.shop.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShopProfits {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("商家编号")
    private String shop_id;
    @ApiModelProperty("用户编号")
    private String user_id;
    @ApiModelProperty("累计收益金额")
    private String total_profit;
    @ApiModelProperty("累计抢单提现汇总金额")
    private String grab_cash;
    @ApiModelProperty("淘金金额提现汇总金额")
    private String lode_cash;
    @ApiModelProperty("专属订单提现汇总金额")
    private String shop_order_cash;
    @ApiModelProperty("抢单奖励提现汇总金额")
    private String withdraw_cash;
    @ApiModelProperty("抢单奖励金额")
    private String reward;
    @ApiModelProperty("累计抢单成交")
    private String grab;
    @ApiModelProperty("淘金金额")
    private String lode;
    @ApiModelProperty("专属订单金额")
    private String shop_order;
    @ApiModelProperty("累计冻结收益金额")
    private String profit_frozen;
    @ApiModelProperty("冻结抢单奖励金额")
    private String reward_frozen;
    @ApiModelProperty("冻结累计抢单成交")
    private String grab_frozen;
    @ApiModelProperty("冻结淘金金额")
    private String lode_frozen;
    @ApiModelProperty("冻结专属订单金额")
    private String shop_order_frozen;
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

    public String getTotal_profit() {
        return total_profit;
    }

    public void setTotal_profit(String total_profit) {
        this.total_profit = total_profit;
    }

    public String getGrab_cash() {
        return grab_cash;
    }

    public void setGrab_cash(String grab_cash) {
        this.grab_cash = grab_cash;
    }

    public String getLode_cash() {
        return lode_cash;
    }

    public void setLode_cash(String lode_cash) {
        this.lode_cash = lode_cash;
    }

    public String getShop_order_cash() {
        return shop_order_cash;
    }

    public void setShop_order_cash(String shop_order_cash) {
        this.shop_order_cash = shop_order_cash;
    }

    public String getWithdraw_cash() {
        return withdraw_cash;
    }

    public void setWithdraw_cash(String withdraw_cash) {
        this.withdraw_cash = withdraw_cash;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getGrab() {
        return grab;
    }

    public void setGrab(String grab) {
        this.grab = grab;
    }

    public String getLode() {
        return lode;
    }

    public void setLode(String lode) {
        this.lode = lode;
    }

    public String getShop_order() {
        return shop_order;
    }

    public void setShop_order(String shop_order) {
        this.shop_order = shop_order;
    }

    public String getProfit_frozen() {
        return profit_frozen;
    }

    public void setProfit_frozen(String profit_frozen) {
        this.profit_frozen = profit_frozen;
    }

    public String getReward_frozen() {
        return reward_frozen;
    }

    public void setReward_frozen(String reward_frozen) {
        this.reward_frozen = reward_frozen;
    }

    public String getGrab_frozen() {
        return grab_frozen;
    }

    public void setGrab_frozen(String grab_frozen) {
        this.grab_frozen = grab_frozen;
    }

    public String getLode_frozen() {
        return lode_frozen;
    }

    public void setLode_frozen(String lode_frozen) {
        this.lode_frozen = lode_frozen;
    }

    public String getShop_order_frozen() {
        return shop_order_frozen;
    }

    public void setShop_order_frozen(String shop_order_frozen) {
        this.shop_order_frozen = shop_order_frozen;
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