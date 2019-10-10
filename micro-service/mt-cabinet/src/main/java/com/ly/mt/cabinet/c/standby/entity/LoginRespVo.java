package com.ly.mt.cabinet.c.standby.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("登录响应对象")
public class LoginRespVo {
    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("手机号")
    private String phone;

    public LoginRespVo(String token, String phone) {
        this.token = token;
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
