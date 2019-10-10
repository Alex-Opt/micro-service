package com.ly.mt.cabinet.b.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel
public class CabinetStoreResVo{


    @ApiModelProperty("店铺id")
    private String id;

    @ApiModelProperty("店铺名称")
    private String name;

    @ApiModelProperty("店铺地址")
    private String address;

    @ApiModelProperty("详细地址")
    private String detail_address;

    @ApiModelProperty("创建时间")
    private String gmt_create;

    @ApiModelProperty("更新时间")
    private String gmt_modify;

    @ApiModelProperty("店铺状态：0:待创建  1:已创建  2:已使用")
    private String create_status;

    @ApiModelProperty("店主姓名")
    private String owner_name;

    @ApiModelProperty("店主手机")
    private String owner_phone;

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

    public String getCreate_status() {
        return create_status;
    }

    public void setCreate_status(String create_status) {
        this.create_status = create_status;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_phone() {
        return owner_phone;
    }

    public void setOwner_phone(String owner_phone) {
        this.owner_phone = owner_phone;
    }
}
