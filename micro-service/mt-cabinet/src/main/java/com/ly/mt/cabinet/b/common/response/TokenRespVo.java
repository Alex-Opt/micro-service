package com.ly.mt.cabinet.b.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("token响应对象")
public class TokenRespVo {

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("角色类型1:BD 2:仓库管理员  3:店铺管理员  4:店铺员工 5：未知")
    private String roleType;

    @ApiModelProperty("手机号")
    private String phone;

    public TokenRespVo(String token, String roleType ,String phone) {
        this.token = token;
        this.roleType = roleType;
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
