package com.ly.mt.cabinet.b.common.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class StorePropReqVo {
    @ApiModelProperty(name = "店铺类型")
    private int store_type;
    @ApiModelProperty(name = "人流量")
    private int forcast_flow;
    @ApiModelProperty(name = "装修类型")
    private int decorate_type;
    @ApiModelProperty(name = "是否连锁")
    private int is_chain;
    @ApiModelProperty(name = "人均消费")
    private int per_capita_expense;
    @ApiModelProperty(name = "店铺面积")
    private int use_area;

    public int getUse_area() {
        return use_area;
    }

    public void setUse_area(int use_area) {
        this.use_area = use_area;
    }

    public int getStore_type() {
        return store_type;
    }

    public void setStore_type(int store_type) {
        this.store_type = store_type;
    }

    public int getForcast_flow() {
        return forcast_flow;
    }

    public void setForcast_flow(int forcast_flow) {
        this.forcast_flow = forcast_flow;
    }

    public int getDecorate_type() {
        return decorate_type;
    }

    public void setDecorate_type(int decorate_type) {
        this.decorate_type = decorate_type;
    }

    public int getIs_chain() {
        return is_chain;
    }

    public void setIs_chain(int is_chain) {
        this.is_chain = is_chain;
    }

    public int getPer_capita_expense() {
        return per_capita_expense;
    }

    public void setPer_capita_expense(int per_capita_expense) {
        this.per_capita_expense = per_capita_expense;
    }
}
