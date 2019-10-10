package com.ly.mt.cabinet.b.replenish.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "sku信息对象")
public class GoodsSkuInfoVo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("sku名称")
    private String name;
    @ApiModelProperty("skuCode")
    private String code;
    @ApiModelProperty("spuId")
    private String spuId;
    @ApiModelProperty("库存数量")
    private int stock=0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}