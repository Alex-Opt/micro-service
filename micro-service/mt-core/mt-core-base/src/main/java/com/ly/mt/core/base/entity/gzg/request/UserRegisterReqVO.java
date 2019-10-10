package com.ly.mt.core.base.entity.gzg.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/** @deprecated */
@ApiModel
public class UserRegisterReqVO {
    @ApiModelProperty
    private String phoneNo;
    @ApiModelProperty
    private String dynamicCode;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDynamicCode() {
        return dynamicCode;
    }

    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
    }
}
