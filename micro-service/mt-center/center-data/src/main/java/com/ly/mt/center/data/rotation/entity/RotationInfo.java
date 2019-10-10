package com.ly.mt.center.data.rotation.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RotationInfo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("图片url")
    private String picture_url;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("排序号,排序号最小的作为主图，从1开始")
    private String sort_number;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("修改时间")
    private String modify_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSort_number() {
        return sort_number;
    }

    public void setSort_number(String sort_number) {
        this.sort_number = sort_number;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

}