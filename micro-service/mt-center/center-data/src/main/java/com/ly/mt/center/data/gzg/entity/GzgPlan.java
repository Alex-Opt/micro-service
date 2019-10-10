package com.ly.mt.center.data.gzg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgPlan {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("方案名称")
    private String plan_name;
    @ApiModelProperty("格子1中产品skuid值")
    private String one;
    @ApiModelProperty("格子2中产品skuid值")
    private String two;
    @ApiModelProperty("格子3中产品skuid值")
    private String three;
    @ApiModelProperty("格子4中产品skuid值")
    private String four;
    @ApiModelProperty("格子5中产品skuid值")
    private String five;
    @ApiModelProperty("格子6中产品skuid值")
    private String six;
    @ApiModelProperty("格子7中产品skuid值")
    private String seven;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public String getFive() {
        return five;
    }

    public void setFive(String five) {
        this.five = five;
    }

    public String getSix() {
        return six;
    }

    public void setSix(String six) {
        this.six = six;
    }

    public String getSeven() {
        return seven;
    }

    public void setSeven(String seven) {
        this.seven = seven;
    }

}