package com.ly.mt.cabinet.c.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author gongj
 * 订单模型-封装前端入参订单重要参数的模型，不需要extends BaseEntity
 * @String 2019/5/21
 */

@ApiModel(value = "WebOrderVo", description = "封装前端订单对象")
public class WebOrderVo {
    @ApiModelProperty(value = "柜子唯一标志码", name = "imei", required = true, example = "865800042529062")
    private String imei;

    @ApiModelProperty(value = "用户id", name = "userId", required = false, example = "580842177651937280")
    private String userId;

    @ApiModelProperty(value = "订单中商品属性", name = "webGZGS", required = true, example = "[{\"skuId\":\"112492582466\",\"cabinetNo\":1}]")
    private List<WebGZG> skuIds;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public List<WebGZG> getSkuIds() {
        return skuIds;
    }

    public void setSkuIds(List<WebGZG> skuIds) {
        this.skuIds = skuIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}