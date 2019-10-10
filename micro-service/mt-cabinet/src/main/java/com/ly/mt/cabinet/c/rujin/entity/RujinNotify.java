package com.ly.mt.cabinet.c.rujin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RujinNotify {

    @ApiModelProperty("订单编号")
    private String order;
    @ApiModelProperty("终端开门状态： 1=成功， 2=故障")
    private String state;
    @ApiModelProperty("签名算法")
    private String token;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "RujinNotify{" +
                ", order='" + order + '\'' +
                ", state='" + state + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}