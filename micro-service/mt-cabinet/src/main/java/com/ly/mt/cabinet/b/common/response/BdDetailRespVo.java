package com.ly.mt.cabinet.b.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 数据统计vo
 */
@ApiModel("数据统计受特vo")
public class BdDetailRespVo {


    @ApiModelProperty("总订单数")
    private String order_count;


    @ApiModelProperty("订单总金额")
    private String order_amount;

    @ApiModelProperty("总利润")
    private String profit_amount;

    @ApiModelProperty("总店铺数量")
    private String store_num;


    public String getOrder_count() {
        return order_count;
    }

    public void setOrder_count(String order_count) {
        this.order_count = order_count;
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

    public String getStore_num() {
        return store_num;
    }

    public void setStore_num(String store_num) {
        this.store_num = store_num;
    }
}
