package com.ly.mt.center.data.goods.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GoodsAttr {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("类目Id，商品类目表的Id")
    private String cid;
    @ApiModelProperty("属性名称")
    private String attr_name;
    @ApiModelProperty("属性状态 1：正常，2：停用")
    private String attr_status;
    @ApiModelProperty("排序号")
    private String sort_num;
    @ApiModelProperty("创建时间")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getAttr_name() {
        return attr_name;
    }

    public void setAttr_name(String attr_name) {
        this.attr_name = attr_name;
    }

    public String getAttr_status() {
        return attr_status;
    }

    public void setAttr_status(String attr_status) {
        this.attr_status = attr_status;
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

}