package com.ly.mt.center.data.goods.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GoodsSkuPrice {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("商品SPU编号")
    private String spu_id;
    @ApiModelProperty("商品SKU编号")
    private String sku_id;
    @ApiModelProperty("价格名称，如 用户等级价格")
    private String price_name;
    @ApiModelProperty("价格")
    private String sku_price;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("创建时间")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getPrice_name() {
        return price_name;
    }

    public void setPrice_name(String price_name) {
        this.price_name = price_name;
    }

    public String getSku_price() {
        return sku_price;
    }

    public void setSku_price(String sku_price) {
        this.sku_price = sku_price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}