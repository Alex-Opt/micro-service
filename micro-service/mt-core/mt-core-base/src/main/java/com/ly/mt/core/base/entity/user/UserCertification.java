package com.ly.mt.core.base.entity.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserCertification {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("用户编号")
    private String user_id;
    @ApiModelProperty("身份证姓名")
    private String card_name;
    @ApiModelProperty("身份证号码")
    private String card_id;
    @ApiModelProperty("电话号码")
    private String mobile;
    @ApiModelProperty("身份证正面照片url")
    private String card_front_url;
    @ApiModelProperty("身份证背面照片url")
    private String card_reverse_url;
    @ApiModelProperty("认证状态 0:待审核 1:已认证 2:未成年")
    private String status;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("更新时间")
    private String modify_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCard_front_url() {
        return card_front_url;
    }

    public void setCard_front_url(String card_front_url) {
        this.card_front_url = card_front_url;
    }

    public String getCard_reverse_url() {
        return card_reverse_url;
    }

    public void setCard_reverse_url(String card_reverse_url) {
        this.card_reverse_url = card_reverse_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }
}
