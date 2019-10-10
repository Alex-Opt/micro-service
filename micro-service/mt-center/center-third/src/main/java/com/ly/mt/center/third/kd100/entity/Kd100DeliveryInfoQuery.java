package com.ly.mt.center.third.kd100.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("快递100查询物流信息实体")
public class Kd100DeliveryInfoQuery {
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty(value = "物流公司编码", required = true)
    private String com;
    @ApiModelProperty(value = "快递单号", required = true)
    private String num;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}