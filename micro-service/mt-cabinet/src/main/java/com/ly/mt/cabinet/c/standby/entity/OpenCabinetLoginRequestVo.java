package com.ly.mt.cabinet.c.standby.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("维护人员登陆请求入参")
public class OpenCabinetLoginRequestVo {

    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("验证码")
    private String code;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
