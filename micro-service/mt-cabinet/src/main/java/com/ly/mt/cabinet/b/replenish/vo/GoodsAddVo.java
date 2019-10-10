package com.ly.mt.cabinet.b.replenish.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "展柜添加商品对象")
public class GoodsAddVo {
    @ApiModelProperty(value = "skuId", required = true)
    private String skuId;
    @ApiModelProperty(value = "spuId", required = true)
    private String spuId;
    @ApiModelProperty(value = "cid商品类目id", required = true)
    private String cid;
    @ApiModelProperty("本次添加数量")
    private int stock;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}