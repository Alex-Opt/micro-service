package com.ly.mt.center.data.trade.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class TradeOrderMedia {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("订单Id")
    private String orderId;
    @ApiModelProperty("媒体活动页面类型")
    private String type;
    @ApiModelProperty("媒体素材")
    private String material;
    @ApiModelProperty("渠道")
    private String channel;
    @ApiModelProperty("备用字段1")
    private String col1;
    @ApiModelProperty("备用字段2")
    private String col2;
    @ApiModelProperty("创建时间")
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}