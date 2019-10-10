package com.ly.mt.cabinet.b.login.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录结果
*/
@ApiModel(value="登录结果信息")
public class LoginResultVo {
	@ApiModelProperty(value = "用户token", required = true)
	private String token;
	@ApiModelProperty(value = "角色类型1:BD 2:仓库管理员  3:店铺管理员  4:店铺员工 5：未知", required = true)
	private String roleType;

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
}
