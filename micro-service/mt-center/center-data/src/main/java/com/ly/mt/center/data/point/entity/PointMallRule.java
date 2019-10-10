package com.ly.mt.center.data.point.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PointMallRule {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("规则名称")
    private String title;
    @ApiModelProperty("规则说明")
    private String rule_desc;
    @ApiModelProperty("规则状态 1、正常状态，2、停用状态，3、过期状态")
    private String rule_status;
    @ApiModelProperty("兑换类型 1、商品，2、优惠券")
    private String exchange_type;
    @ApiModelProperty("兑换积分")
    private String score;
    @ApiModelProperty("有效期开始时间")
    private String start_time;
    @ApiModelProperty("有效期结束时间")
    private String end_time;
    @ApiModelProperty("创建时间")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRule_desc() {
        return rule_desc;
    }

    public void setRule_desc(String rule_desc) {
        this.rule_desc = rule_desc;
    }

    public String getRule_status() {
        return rule_status;
    }

    public void setRule_status(String rule_status) {
        this.rule_status = rule_status;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}