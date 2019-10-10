package com.ly.mt.center.data.gzg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgInfo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("格子柜唯一code码")
    private String code;
    @ApiModelProperty("柜子所在酒店id")
    private String hotel_id;
    @ApiModelProperty("格子柜位置")
    private String position;
    @ApiModelProperty("柜子对应配货方案id")
    private String plan_id;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("价格")
    private String price;
    @ApiModelProperty("购买时间")
    private String buy_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBuy_time() {
        return buy_time;
    }

    public void setBuy_time(String buy_time) {
        this.buy_time = buy_time;
    }

}