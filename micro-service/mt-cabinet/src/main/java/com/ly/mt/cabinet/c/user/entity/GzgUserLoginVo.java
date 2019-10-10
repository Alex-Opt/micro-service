package com.ly.mt.cabinet.c.user.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 用户登录vo类
 * @Author gongjy
 */
@ApiModel
public class GzgUserLoginVo {
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("验证码")
    private String dynamicCode;

    public GzgUserLoginVo() {
    }

    public GzgUserLoginVo(String mobile, String dynamicCode) {
        this.mobile = mobile;
        this.dynamicCode = dynamicCode;
    }

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

    @Override
    public String toString() {
        return "GzgUserLoginVo{" +
                "mobile='" + mobile + '\'' +
                ", dynamicCode='" + dynamicCode + '\'' +
                '}';
    }
}