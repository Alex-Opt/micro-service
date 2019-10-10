package com.ly.mt.cabinet.c.cabinet.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgCabinet {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("内阁编号")
    private String cabinet_no;
    @ApiModelProperty("格子柜编号")
    private String gzg_code;
    @ApiModelProperty("创建时间")
    private String gmt_create;
    @ApiModelProperty("修改时间")
    private String gmt_modify;
    @ApiModelProperty("货物销量")
    private String sell_no;
    @ApiModelProperty("0：待补货  1：已补货")
    private String status;
    @ApiModelProperty("")
    private String placeholder_1;
    @ApiModelProperty("")
    private String placeholder_2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCabinet_no() {
        return cabinet_no;
    }

    public void setCabinet_no(String cabinet_no) {
        this.cabinet_no = cabinet_no;
    }

    public String getGzg_code() {
        return gzg_code;
    }

    public void setGzg_code(String gzg_code) {
        this.gzg_code = gzg_code;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }

    public String getGmt_modify() {
        return gmt_modify;
    }

    public void setGmt_modify(String gmt_modify) {
        this.gmt_modify = gmt_modify;
    }

    public String getSell_no() {
        return sell_no;
    }

    public void setSell_no(String sell_no) {
        this.sell_no = sell_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlaceholder_1() {
        return placeholder_1;
    }

    public void setPlaceholder_1(String placeholder_1) {
        this.placeholder_1 = placeholder_1;
    }

    public String getPlaceholder_2() {
        return placeholder_2;
    }

    public void setPlaceholder_2(String placeholder_2) {
        this.placeholder_2 = placeholder_2;
    }
}