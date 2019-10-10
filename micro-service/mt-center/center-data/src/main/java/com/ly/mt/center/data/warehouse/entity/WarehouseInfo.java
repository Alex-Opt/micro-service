package com.ly.mt.center.data.warehouse.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class WarehouseInfo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("仓库名称")
    private String name;
    @ApiModelProperty("联系人")
    private String linkman;
    @ApiModelProperty("电话")
    private String phone;
    @ApiModelProperty("手机")
    private String mobile;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("管易代码")
    private String gy_code;
    @ApiModelProperty("管易名称")
    private String gy_name;
    @ApiModelProperty("创建时间")
    private String create_time;


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

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGy_code() {
        return gy_code;
    }

    public void setGy_code(String gy_code) {
        this.gy_code = gy_code;
    }

    public String getGy_name() {
        return gy_name;
    }

    public void setGy_name(String gy_name) {
        this.gy_name = gy_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}