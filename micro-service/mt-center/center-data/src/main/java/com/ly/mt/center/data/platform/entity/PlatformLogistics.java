package com.ly.mt.center.data.platform.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PlatformLogistics {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("物流公司名称")
    private String company_name;
    @ApiModelProperty("物流公司代码")
    private String company_code;
    @ApiModelProperty("物流公司联系人")
    private String linkman;
    @ApiModelProperty("电话")
    private String phone;
    @ApiModelProperty("详细地址")
    private String address;
    @ApiModelProperty("邮政编码")
    private String postcode;
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

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
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