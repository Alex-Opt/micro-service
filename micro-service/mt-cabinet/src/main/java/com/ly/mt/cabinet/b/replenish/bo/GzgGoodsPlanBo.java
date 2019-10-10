package com.ly.mt.cabinet.b.replenish.bo;


import io.swagger.annotations.ApiModelProperty;

public class GzgGoodsPlanBo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("格子柜的唯一编码值")
    private String imei;
    @ApiModelProperty("格子柜的具体格子号")
    private String cabinetNo;
    @ApiModelProperty("格子柜货skuid")
    private String skuId;
    @ApiModelProperty("货物销量")
    private String sellNo;
    @ApiModelProperty("库存")
    private String stock;
    @ApiModelProperty("格子柜类型 1亿联 2展柜 3如金")
    private String cabinetType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getCabinetNo() {
        return cabinetNo;
    }

    public void setCabinetNo(String cabinetNo) {
        this.cabinetNo = cabinetNo;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSellNo() {
        return sellNo;
    }

    public void setSellNo(String sellNo) {
        this.sellNo = sellNo;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getCabinetType() {
        return cabinetType;
    }

    public void setCabinetType(String cabinetType) {
        this.cabinetType = cabinetType;
    }
}