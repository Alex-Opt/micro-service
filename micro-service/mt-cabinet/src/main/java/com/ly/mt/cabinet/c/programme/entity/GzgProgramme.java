package com.ly.mt.cabinet.c.programme.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgProgramme {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("方案名称")
    private String name;
    @ApiModelProperty("货道编号")
    private String cabinet_no;
    @ApiModelProperty("商品sku_id")
    private String sku_id;
    @ApiModelProperty("初始库存")
    private String stock;
    @ApiModelProperty("创建时间")
    private String create_time;

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

    public String getCabinet_no() {
        return cabinet_no;
    }

    public void setCabinet_no(String cabinet_no) {
        this.cabinet_no = cabinet_no;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}