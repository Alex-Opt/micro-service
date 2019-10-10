package com.ly.mt.center.data.cabinet.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class CabinetOptions {
    @ApiModelProperty
    private int id;
    @ApiModelProperty
    private String option_key;
    @ApiModelProperty
    private String option_value;
    @ApiModelProperty
    private int type;
    @ApiModelProperty
    private int status;
    @ApiModelProperty
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmt_create;
    @ApiModelProperty
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmt_modify;

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public void setGmt_modify(Date gmt_modify) {
        this.gmt_modify = gmt_modify;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOption_key(String option_key) {
        this.option_key = option_key;
    }

    public void setOption_value(String option_value) {
        this.option_value = option_value;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getGmt_create() {
        return gmt_create;
    }

    public Date getGmt_modify() {
        return gmt_modify;
    }

    public int getStatus() {
        return status;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getOption_key() {
        return option_key;
    }

    public String getOption_value() {
        return option_value;
    }
}
