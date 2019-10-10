package com.ly.mt.blue.tooth.login.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录结果
*/
@ApiModel(value="登录结果信息")
public class LoginResultVo {
	@ApiModelProperty(value = "用户id", required = true)
	private String userId;
	@ApiModelProperty(value = "用户token", required = true)
	private String token;
	@ApiModelProperty(value = "密码状态 0：已设置 1:未设置", required = true)
	private String status;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
