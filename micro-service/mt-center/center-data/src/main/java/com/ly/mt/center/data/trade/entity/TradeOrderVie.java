package com.ly.mt.center.data.trade.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TradeOrderVie {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("抢单人Id")
    private String seller_id;
    @ApiModelProperty("订单Id")
    private String order_id;
    @ApiModelProperty("抢单状态 1：未抢，2：锁定，3：已抢")
    private String vie_status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getVie_status() {
        return vie_status;
    }

    public void setVie_status(String vie_status) {
        this.vie_status = vie_status;
    }

}