package com.ly.mt.center.data.gzg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgHotelStock {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("酒店ID")
    private String hotel_id;
    @ApiModelProperty("skuID")
    private String sku_id;
    @ApiModelProperty("sku名称")
    private String sku_name;
    @ApiModelProperty("sku码")
    private String sku_code;
    @ApiModelProperty("库存总数")
    private String total_num;
    @ApiModelProperty("售出总数")
    private String sell_out_num;
    @ApiModelProperty("退货总数")
    private String return_num;
    @ApiModelProperty("剩余总数")
    private String surplus_num;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getSku_name() {
        return sku_name;
    }

    public void setSku_name(String sku_name) {
        this.sku_name = sku_name;
    }

    public String getSku_code() {
        return sku_code;
    }

    public void setSku_code(String sku_code) {
        this.sku_code = sku_code;
    }

    public String getTotal_num() {
        return total_num;
    }

    public void setTotal_num(String total_num) {
        this.total_num = total_num;
    }

    public String getSell_out_num() {
        return sell_out_num;
    }

    public void setSell_out_num(String sell_out_num) {
        this.sell_out_num = sell_out_num;
    }

    public String getReturn_num() {
        return return_num;
    }

    public void setReturn_num(String return_num) {
        this.return_num = return_num;
    }

    public String getSurplus_num() {
        return surplus_num;
    }

    public void setSurplus_num(String surplus_num) {
        this.surplus_num = surplus_num;
    }

}