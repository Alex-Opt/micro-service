package com.ly.mt.center.data.trade.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TradeDistributionMode {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("配送方式名称")
    private String distribution_name;
    @ApiModelProperty("配送价格")
    private String ddistribution_price;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistribution_name() {
        return distribution_name;
    }

    public void setDistribution_name(String distribution_name) {
        this.distribution_name = distribution_name;
    }

    public String getDdistribution_price() {
        return ddistribution_price;
    }

    public void setDdistribution_price(String ddistribution_price) {
        this.ddistribution_price = ddistribution_price;
    }

}