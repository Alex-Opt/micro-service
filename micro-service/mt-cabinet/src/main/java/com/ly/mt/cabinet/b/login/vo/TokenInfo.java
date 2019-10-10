package com.ly.mt.cabinet.b.login.vo;

/**
* @program: mt-cabinet
* @description: token信息
* @author: wanghongliang
* @create: 2019/9/3 20:36
**/
public class TokenInfo {
	private String token;//用户token
	private String userId;//用户id
	private String phone;//手机号
	private String project;//token所属项目名称
	private String startTime;//token生成时间

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
}
