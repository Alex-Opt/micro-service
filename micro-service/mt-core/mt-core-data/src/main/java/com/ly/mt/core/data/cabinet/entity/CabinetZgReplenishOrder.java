package com.ly.mt.core.data.cabinet.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 格子柜B端补货：展柜补货单订单表
 *
 * @author wanghongliang
 * @date 2019-09-18
 */
public class CabinetZgReplenishOrder {

    @ApiModelProperty(value = "本次需要补货的展柜数量", required = true)
    private int num;

    @ApiModelProperty(value = "推送手机号", required = true)
    private String phone;

    @ApiModelProperty(value = "用户id", required = true)
    private String user_id;

    @ApiModelProperty(value = "状态 0：待补货  1：已完成", required = true)
    private String status;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
