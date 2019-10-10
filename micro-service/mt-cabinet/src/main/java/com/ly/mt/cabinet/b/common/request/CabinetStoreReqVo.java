package com.ly.mt.cabinet.b.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CabinetStoreReqVo {
    @ApiModelProperty
    private String id;
    @ApiModelProperty
    private String name;
    @ApiModelProperty
    private String address;
    @ApiModelProperty
    private String detail_address;
    @ApiModelProperty
    private String belong_circle;
    @ApiModelProperty
    private String create_status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetail_address() {
        return detail_address;
    }

    public void setDetail_address(String detail_address) {
        this.detail_address = detail_address;
    }

    public String getBelong_circle() {
        return belong_circle;
    }

    public void setBelong_circle(String belong_circle) {
        this.belong_circle = belong_circle;
    }

    public String getCreate_status() {
        return create_status;
    }

    public void setCreate_status(String create_status) {
        this.create_status = create_status;
    }
}
