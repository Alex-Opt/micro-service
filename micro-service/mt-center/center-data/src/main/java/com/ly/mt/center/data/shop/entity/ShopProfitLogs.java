package com.ly.mt.center.data.shop.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShopProfitLogs {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("订单编号（可以为空）")
    private String order_id;
    @ApiModelProperty("进货订单编号（可以为空）")
    private String shop_purchases_id;
    @ApiModelProperty("获取奖励商家编号")
    private String shop_id;
    @ApiModelProperty("获取奖励用户编号")
    private String user_id;
    @ApiModelProperty("订单提供者店铺编号")
    private String provider_shop_id;
    @ApiModelProperty("订单提供者编号")
    private String provider_id;
    @ApiModelProperty("收益类型 1：邀请，2：通讯录，3：流量，4：抢单奖励， 5：抢单金额，6：专属订单，8：进货， 10：抢单提现，11：淘金提现，12：专属订单提现， 13：抢单奖励提现，14：进货奖励提现 15：退货（金额为负数）")
    private String profit_type;
    @ApiModelProperty("收益金额")
    private String profit;
    @ApiModelProperty("状态 1：待结算，2正常，3：取消")
    private String status;
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

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getShop_purchases_id() {
        return shop_purchases_id;
    }

    public void setShop_purchases_id(String shop_purchases_id) {
        this.shop_purchases_id = shop_purchases_id;
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

    public String getProvider_shop_id() {
        return provider_shop_id;
    }

    public void setProvider_shop_id(String provider_shop_id) {
        this.provider_shop_id = provider_shop_id;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public String getProfit_type() {
        return profit_type;
    }

    public void setProfit_type(String profit_type) {
        this.profit_type = profit_type;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
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

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

}