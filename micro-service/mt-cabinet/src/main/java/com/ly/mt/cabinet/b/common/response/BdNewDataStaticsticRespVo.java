package com.ly.mt.cabinet.b.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("店铺统计-店铺详情")
public class BdNewDataStaticsticRespVo {


    @ApiModelProperty("店铺id")
    private String store_id;

    @ApiModelProperty("店铺名称")
    private String store_name;

    @ApiModelProperty("订单数")
    private String order_num;

    @ApiModelProperty("订单金额")
    private String order_amount;

    @ApiModelProperty("利润")
    private String profit_amount;

    @ApiModelProperty("库存/待补")
    private String stock_num_replenish;

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getProfit_amount() {
        return profit_amount;
    }

    public void setProfit_amount(String profit_amount) {
        this.profit_amount = profit_amount;
    }

    public String getStock_num_replenish() {
        return stock_num_replenish;
    }

    public void setStock_num_replenish(String stock_num_replenish) {
        this.stock_num_replenish = stock_num_replenish;
    }
}
