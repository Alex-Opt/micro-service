package com.ly.mt.cabinet.c.user.entity;

import io.swagger.annotations.ApiModelProperty;

public class GzgUserRegisterVo {
    @ApiModelProperty(value = "用户id", required = true)
    private String id;

    @ApiModelProperty("电话")
    private String mobile;

    @ApiModelProperty("微信的OpenId")
    private String wx_open_id;


    @ApiModelProperty("用户类型（1：普通用户，2：企业用户，3：卖家用户，4：平台用户）")
    private String user_type;

    @ApiModelProperty("有效状态")
    private String valid_status;

    @ApiModelProperty(value = "当前用户状态", required = true)
    private String user_status;


    @ApiModelProperty("审核备注")
    private String audit_remark;
    @ApiModelProperty(value = "注册来源", required = true)
    private String quick_type;

    @ApiModelProperty(value = "项目类型",required = true)
    private String project_type;

    @ApiModelProperty("头像url")
    private String avatar_pic_src;
    @ApiModelProperty("用户名")
    private String login_name;

    @ApiModelProperty("通知配置(10支付退款异常通知)")
    private String notice_config;
    @ApiModelProperty(value = "创建时间", required = true)
    private String create_time;


    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWx_open_id() {
        return wx_open_id;
    }

    public void setWx_open_id(String wx_open_id) {
        this.wx_open_id = wx_open_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getValid_status() {
        return valid_status;
    }

    public void setValid_status(String valid_status) {
        this.valid_status = valid_status;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getAudit_remark() {
        return audit_remark;
    }

    public void setAudit_remark(String audit_remark) {
        this.audit_remark = audit_remark;
    }

    public String getQuick_type() {
        return quick_type;
    }

    public void setQuick_type(String quick_type) {
        this.quick_type = quick_type;
    }

    public String getAvatar_pic_src() {
        return avatar_pic_src;
    }

    public void setAvatar_pic_src(String avatar_pic_src) {
        this.avatar_pic_src = avatar_pic_src;
    }

    public String getNotice_config() {
        return notice_config;
    }

    public void setNotice_config(String notice_config) {
        this.notice_config = notice_config;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
