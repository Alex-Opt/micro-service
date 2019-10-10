package com.ly.mt.mall.h5.login.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("帐号密码登录vo")
public class LoginByLoginNameVo {
    @ApiModelProperty(value = "帐号/手机号", required = true)
    private String loginName;
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "用户id", hidden = true)
    private String id;
    @ApiModelProperty(value = "用户姓名", hidden = true)
    private String userName;
    @ApiModelProperty(value = "用户手机号", hidden = true)
    private String mobile;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}