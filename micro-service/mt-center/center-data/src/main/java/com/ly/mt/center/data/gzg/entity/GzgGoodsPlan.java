package com.ly.mt.center.data.gzg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgGoodsPlan {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;

    @ApiModelProperty("格子柜的唯一编码值")
    private String imei;

    @ApiModelProperty("格子柜的具体格子号")
    private String cabinet_no;

    @ApiModelProperty("格子柜货物条形码")
    private String bar_code;

    @ApiModelProperty("格子柜货skuid")
    private String sku_id;

    @ApiModelProperty("方案名称")
    private String plan_name;

    @ApiModelProperty("货物销量")
    private String sell_no;

    @ApiModelProperty("库存")
    private String stock;

    @ApiModelProperty("0:不可用，1：正常使用")
    private String state;

    @ApiModelProperty("创建时间")
    private String create_time;

    @ApiModelProperty("格子柜类型 1亿联 2展柜 3如金")
    private String cabinet_type;

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

    public String getCabinet_no() {
        return cabinet_no;
    }

    public void setCabinet_no(String cabinet_no) {
        this.cabinet_no = cabinet_no;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public String getSell_no() {
        return sell_no;
    }

    public void setSell_no(String sell_no) {
        this.sell_no = sell_no;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCabinet_type() {
        return cabinet_type;
    }

    public void setCabinet_type(String cabinet_type) {
        this.cabinet_type = cabinet_type;
    }

    @Override
    public String toString() {
        return "GzgGoodsPlan{" +
                "id='" + id + '\'' +
                ", imei='" + imei + '\'' +
                ", cabinet_no='" + cabinet_no + '\'' +
                ", bar_code='" + bar_code + '\'' +
                ", sku_id='" + sku_id + '\'' +
                ", plan_name='" + plan_name + '\'' +
                ", sell_no='" + sell_no + '\'' +
                ", stock='" + stock + '\'' +
                ", state='" + state + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}