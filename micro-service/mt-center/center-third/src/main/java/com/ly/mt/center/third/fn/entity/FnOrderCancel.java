package com.ly.mt.center.third.fn.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("蜂鸟订单取消实体")
public class FnOrderCancel {
    @ApiModelProperty(value = "订单号", required = true)
    private String partner_order_code;
    @ApiModelProperty(value = "订单取消原因代码", required = true)
    private String order_cancel_reason_code;
    @ApiModelProperty(value = "订单取消编码", required = true)
    private String order_cancel_code;
    @ApiModelProperty(value = "订单取消描述", hidden = true)
    private String order_cancel_description;
    @ApiModelProperty(value = "订单取消时间（毫秒）", hidden = true)
    private String order_cancel_time;

    public String getPartner_order_code() {
        return partner_order_code;
    }

    public void setPartner_order_code(String partner_order_code) {
        this.partner_order_code = partner_order_code;
    }

    public String getOrder_cancel_reason_code() {
        return order_cancel_reason_code;
    }

    public void setOrder_cancel_reason_code(String order_cancel_reason_code) {
        this.order_cancel_reason_code = order_cancel_reason_code;
    }

    public String getOrder_cancel_code() {
        return order_cancel_code;
    }

    public void setOrder_cancel_code(String order_cancel_code) {
        this.order_cancel_code = order_cancel_code;
    }

    public String getOrder_cancel_description() {
        return order_cancel_description;
    }

    public void setOrder_cancel_description(String order_cancel_description) {
        this.order_cancel_description = order_cancel_description;
    }

    public String getOrder_cancel_time() {
        return order_cancel_time;
    }

    public void setOrder_cancel_time(String order_cancel_time) {
        this.order_cancel_time = order_cancel_time;
    }
}