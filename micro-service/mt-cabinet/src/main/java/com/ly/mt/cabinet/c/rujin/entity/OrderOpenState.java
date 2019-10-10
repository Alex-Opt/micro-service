package com.ly.mt.cabinet.c.rujin.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class OrderOpenState {
    @ApiModelProperty("业务订单号")
    private  String order;

    @ApiModelProperty("柜子开门状态 1=开门成功   2=该货道有故障")
    private String state;

    @ApiModelProperty("签名 MD5（终端ID+订单号+skey）")
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
        return "OrderOpenState{" +
                "order='" + order + '\'' +
                ", state='" + state + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
