package com.ly.mt.center.data.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserPunchCard {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("用户编号")
    private String user_id;
    @ApiModelProperty("用户姓名")
    private String user_name;
    @ApiModelProperty("打卡时间")
    private String punch_time;
    @ApiModelProperty("创建时间")
    private String create_time;


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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPunch_time() {
        return punch_time;
    }

    public void setPunch_time(String punch_time) {
        this.punch_time = punch_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}