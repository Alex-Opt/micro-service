package com.ly.mt.center.data.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserReals {
    @ApiModelProperty("用户编号")
    private String user_id;
    @ApiModelProperty("真实姓名")
    private String name;
    @ApiModelProperty("身份证正面")
    private String idcard_front;
    @ApiModelProperty("身份证背面")
    private String idcard_back;
    @ApiModelProperty("身份证号码")
    private String idcard;
    @ApiModelProperty("有效期开始日期")
    private String valid_started_at;
    @ApiModelProperty("有效期结束日期")
    private String valid_ended_at;
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("更新时间")
    private String modify_time;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard_front() {
        return idcard_front;
    }

    public void setIdcard_front(String idcard_front) {
        this.idcard_front = idcard_front;
    }

    public String getIdcard_back() {
        return idcard_back;
    }

    public void setIdcard_back(String idcard_back) {
        this.idcard_back = idcard_back;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getValid_started_at() {
        return valid_started_at;
    }

    public void setValid_started_at(String valid_started_at) {
        this.valid_started_at = valid_started_at;
    }

    public String getValid_ended_at() {
        return valid_ended_at;
    }

    public void setValid_ended_at(String valid_ended_at) {
        this.valid_ended_at = valid_ended_at;
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