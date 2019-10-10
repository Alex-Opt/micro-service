package com.ly.mt.center.data.trade.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TradeOrderMqLog {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("订单编号")
    private String order_id;
    @ApiModelProperty("发送状态 1：成功，2：失败")
    private String send_status;
    @ApiModelProperty("发送时间")
    private String create_time;


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

    public String getSend_status() {
        return send_status;
    }

    public void setSend_status(String send_status) {
        this.send_status = send_status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}