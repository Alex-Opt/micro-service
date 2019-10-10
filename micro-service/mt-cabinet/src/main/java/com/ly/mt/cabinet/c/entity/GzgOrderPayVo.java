package com.ly.mt.cabinet.c.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgOrderPayVo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("订单id")
    private String order_id;
    @ApiModelProperty("支付交易单号")
    private String pay_id;
    @ApiModelProperty("0：支付支付失败，柜子不打开；1：支付成功，柜子未打开；2：支付成功，柜子打开,3:支付超时")
    private String state;


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

    public String getPay_id() {
        return pay_id;
    }

    public void setPay_id(String pay_id) {
        this.pay_id = pay_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}