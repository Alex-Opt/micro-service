package com.ly.mt.center.data.goods.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GoodsAttrValue {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("属性Id，商品属性表的Id")
    private String attr_id;
    @ApiModelProperty("属性状态 1：正常，2：停用")
    private String attr_status;
    @ApiModelProperty("属性值")
    private String attr_value;
    @ApiModelProperty("排序码")
    private String sort_num;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("更新时间")
    private String modify_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(String attr_id) {
        this.attr_id = attr_id;
    }

    public String getAttr_status() {
        return attr_status;
    }

    public void setAttr_status(String attr_status) {
        this.attr_status = attr_status;
    }

    public String getAttr_value() {
        return attr_value;
    }

    public void setAttr_value(String attr_value) {
        this.attr_value = attr_value;
    }

    public String getSort_num() {
        return sort_num;
    }

    public void setSort_num(String sort_num) {
        this.sort_num = sort_num;
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