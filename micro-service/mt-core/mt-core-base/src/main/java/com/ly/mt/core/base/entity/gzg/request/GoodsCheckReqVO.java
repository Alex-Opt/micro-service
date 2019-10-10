package com.ly.mt.core.base.entity.gzg.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/** @deprecated */
@ApiModel
public class GoodsCheckReqVO {
    @ApiModelProperty
    private long goodsBarCode;

    public long getGoodsBarCode() {
        return goodsBarCode;
    }

    public void setGoodsBarCode(long goodsBarCode) {
        this.goodsBarCode = goodsBarCode;
    }
}
