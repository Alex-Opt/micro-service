package com.ly.mt.cabinet.b.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel
public class LoginReqVO {
    @ApiModelProperty
    @NotBlank
    private String phoneNo;
    @ApiModelProperty
    @NotBlank
    private String dynamicCode;

    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getDynamicCode() {
        return dynamicCode;
    }
}
