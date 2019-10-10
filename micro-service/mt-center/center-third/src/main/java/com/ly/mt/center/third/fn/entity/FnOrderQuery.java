package com.ly.mt.center.third.fn.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("蜂鸟订单查询实体")
public class FnOrderQuery {
    @ApiModelProperty(value = "订单号", required = true)
    private String partner_order_code;

    public String getPartner_order_code() {
        return partner_order_code;
    }

    public void setPartner_order_code(String partner_order_code) {
        this.partner_order_code = partner_order_code;
    }
}