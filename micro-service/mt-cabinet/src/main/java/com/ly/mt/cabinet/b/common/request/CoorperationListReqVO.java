package com.ly.mt.cabinet.b.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CoorperationListReqVO {

    private String phone;
    @ApiModelProperty(value = "页码",name = "page_num",example = "1")
    private String page_num;
    @ApiModelProperty(value = "每页数据",name = "page_size",example = "10")
    private String page_size;
    @ApiModelProperty(value = "类型 1:亿联  2:展柜  3:如金",name = "type",example = "1")
    private String type;
    @ApiModelProperty("搜索条件")
    private String condition;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPage_num() {
        return page_num;
    }

    public void setPage_num(String page_num) {
        this.page_num = page_num;
    }

    public String getPage_size() {
        return page_size;
    }

    public void setPage_size(String page_size) {
        this.page_size = page_size;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
