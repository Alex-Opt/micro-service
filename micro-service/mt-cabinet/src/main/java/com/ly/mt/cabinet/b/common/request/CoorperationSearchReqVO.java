package com.ly.mt.cabinet.b.common.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CoorperationSearchReqVO {
    @ApiModelProperty(value = "条件",name = "condition",example = "13534343435")
    private String condition;
    private String bd_phone;
    @ApiModelProperty(value = "类型 1:亿联  2:展柜  3:如金",name = "type",example = "1")
    private int type;
    @ApiModelProperty("页码")
    private int page_num;
    @ApiModelProperty("每页条数")
    private int page_size;

    public int getPage_num() {
        return page_num;
    }

    public void setPage_num(int page_num) {
        this.page_num = page_num;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public String getBd_phone() {
        return bd_phone;
    }

    public void setBd_phone(String bd_phone) {
        this.bd_phone = bd_phone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
