package com.ly.mt.center.data.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class User {
    @ApiModelProperty(value = "用户id", required = true)
    private Long id;
    @ApiModelProperty("到家B端APP用户唯一码")
    private String client_id;
    @ApiModelProperty("用户姓名")
    private String user_name;
    @ApiModelProperty("电话")
    private String mobile;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("微信的OpenId")
    private String wx_open_id;
    @ApiModelProperty("QQ的OpenId")
    private String qq_open_id;
    @ApiModelProperty(value = "登录账号", required = true)
    private String login_name;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty("用户身份证")
    private String id_card;
    @ApiModelProperty("性别")
    private Integer sex;
    @ApiModelProperty("生日")
    private String birthday;
    @ApiModelProperty("省（直辖市）代码")
    private String province_code;
    @ApiModelProperty("省辖市代码")
    private String city_code;
    @ApiModelProperty("县区代码")
    private String district_code;
    @ApiModelProperty("用户类型（1：普通用户，2：企业用户，3：卖家用户，4：平台用户）")
    private String user_type;
    @ApiModelProperty("昵称")
    private String nick_name;
    @ApiModelProperty("有效状态")
    private String valid_status;
    @ApiModelProperty(value = "当前用户状态", required = true)
    private String user_status;
    @ApiModelProperty("店铺id")
    private String shop_id;
    @ApiModelProperty("审核人（平台用户Id）")
    private String auditor;
    @ApiModelProperty("审核备注")
    private String audit_remark;
    @ApiModelProperty(value = "注册来源", required = true)
    private String quick_type;
    @ApiModelProperty(value = "注册的数据来源", required = true)
    private String data_source;
    @ApiModelProperty(value = "媒体素材号", required = true)
    private String material;
    @ApiModelProperty(value = "媒体渠道", required = true)
    private String channel;
    @ApiModelProperty("头像url")
    private String avatar_pic_src;
    @ApiModelProperty("通知配置(10支付退款异常通知)")
    private String notice_config;
    @ApiModelProperty(value = "创建时间", required = true)
    private String create_time;
    @ApiModelProperty(value = "是否首次登陆 0:是 1:否", required = true)
    private String first_login;
    @ApiModelProperty(value = "项目类型",required = true)
    private String  project_type;

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWx_open_id() {
        return wx_open_id;
    }

    public void setWx_open_id(String wx_open_id) {
        this.wx_open_id = wx_open_id;
    }

    public String getQq_open_id() {
        return qq_open_id;
    }

    public void setQq_open_id(String qq_open_id) {
        this.qq_open_id = qq_open_id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
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

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
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

    public String getData_source() {
        return data_source;
    }

    public void setData_source(String data_source) {
        this.data_source = data_source;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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

    public String getFirst_login() {
        return first_login;
    }

    public void setFirst_login(String first_login) {
        this.first_login = first_login;
    }
}