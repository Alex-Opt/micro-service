package com.ly.mt.mall.h5.login.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("手机号验证码登录vo")
public class LoginByMobileVo {
    @ApiModelProperty(value = "手机号", required = true)
    private String mobile;
    @ApiModelProperty(value = "动态验证码", required = true)
    private String dynamicCode;

    @ApiModelProperty(value = "用户id", hidden = true)
    private String id;
    @ApiModelProperty(value = "用户姓名", hidden = true)
    private String userName;
    @ApiModelProperty(value = "用户帐号", hidden = true)
    private String loginName;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDynamicCode() {
        return dynamicCode;
    }

    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}