package com.ly.mt.cabinet.c.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgDeviceCheckLogVo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("原始数据")
    private String content;
    @ApiModelProperty("创建时间")
    private String gmt_create;
    @ApiModelProperty("修改时间")
    private String gmt_modify;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

}