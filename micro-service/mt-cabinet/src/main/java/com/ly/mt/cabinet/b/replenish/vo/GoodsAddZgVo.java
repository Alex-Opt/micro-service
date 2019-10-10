package com.ly.mt.cabinet.b.replenish.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "展柜添加商品对象")
public class GoodsAddZgVo {
    @ApiModelProperty(value = "展柜编号", required = true)
    private String cabinetCode;

    @ApiModelProperty(value = "商品集合对象", required = true)
    private List<GoodsAddVo> goodsAddVoList;

    public String getCabinetCode() {
        return cabinetCode;
    }

    public void setCabinetCode(String cabinetCode) {
        this.cabinetCode = cabinetCode;
    }

    public List<GoodsAddVo> getGoodsAddVoList() {
        return goodsAddVoList;
    }

    public void setGoodsAddVoList(List<GoodsAddVo> goodsAddVoList) {
        this.goodsAddVoList = goodsAddVoList;
    }
}