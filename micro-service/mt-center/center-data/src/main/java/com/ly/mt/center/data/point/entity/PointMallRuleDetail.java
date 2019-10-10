package com.ly.mt.center.data.point.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PointMallRuleDetail {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("积分兑换规则Id")
    private String point_mall_rule_id;
    @ApiModelProperty("如果主表的exchange_type=1,则该字段为sku_id,如果exchange_type=2，则为优惠活动的Id")
    private String limit_business_id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoint_mall_rule_id() {
        return point_mall_rule_id;
    }

    public void setPoint_mall_rule_id(String point_mall_rule_id) {
        this.point_mall_rule_id = point_mall_rule_id;
    }

    public String getLimit_business_id() {
        return limit_business_id;
    }

    public void setLimit_business_id(String limit_business_id) {
        this.limit_business_id = limit_business_id;
    }

}