package com.ly.mt.center.data.goods.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GoodsBrandInfo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("品牌名称")
    private String brand_name;
    @ApiModelProperty("品牌英文名称")
    private String brand_name_en;
    @ApiModelProperty("品牌logo地址")
    private String brand_logo_url;
    @ApiModelProperty("品牌状态 1：有效， 2：停用")
    private String status;
    @ApiModelProperty("创建日期")
    private String create_time;
    @ApiModelProperty("备注")
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_name_en() {
        return brand_name_en;
    }

    public void setBrand_name_en(String brand_name_en) {
        this.brand_name_en = brand_name_en;
    }

    public String getBrand_logo_url() {
        return brand_logo_url;
    }

    public void setBrand_logo_url(String brand_logo_url) {
        this.brand_logo_url = brand_logo_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}