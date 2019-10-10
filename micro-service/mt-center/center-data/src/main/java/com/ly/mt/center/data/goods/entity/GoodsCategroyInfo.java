package com.ly.mt.center.data.goods.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GoodsCategroyInfo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("父类目Id")
    private String parent_id;
    @ApiModelProperty("类目名称")
    private String name;
    @ApiModelProperty("类目状态 1：有效， 2：:删除")
    private String status;
    @ApiModelProperty("类目级别,1:一级类目;2:二级类目;3:三级类目,目前只支持三级类目")
    private String lev;
    @ApiModelProperty("排序号,排序号越小越靠前")
    private String sort_number;
    @ApiModelProperty("创建日期")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLev() {
        return lev;
    }

    public void setLev(String lev) {
        this.lev = lev;
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

}