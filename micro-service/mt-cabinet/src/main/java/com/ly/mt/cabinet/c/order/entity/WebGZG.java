package com.ly.mt.cabinet.c.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author gongj
 * 订单模型-封装前端入参订单重要参数的模型，不需要extends BaseEntity
 * @String 2019/5/21
 */

@ApiModel(value = "WebGZG", description = "封装前端订单格子属性")
public class WebGZG {
    @ApiModelProperty(value = "商品唯一标识码", name = "skuId", required = true)
    private String skuId;
    @ApiModelProperty(value = "商品所在格子号", name = "cabinetNo", required = true)
    private String cabinetNo;

    public WebGZG() {
    }

    public WebGZG(String skuId, String cabinetNo) {
        this.skuId = skuId;
        this.cabinetNo = cabinetNo;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getCabinetNo() {
        return cabinetNo;
    }

    public void setCabinetNo(String cabinetNo) {
        this.cabinetNo = cabinetNo;
    }

    @Override
    public String toString() {
        return "WebGZG{" +
                "skuId='" + skuId + '\'' +
                ", cabinetNo='" + cabinetNo + '\'' +
                '}';
    }
}