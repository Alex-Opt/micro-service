package com.ly.mt.center.data.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserPointData {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("用户Id")
    private String user_id;
    @ApiModelProperty("积分规则Id")
    private String point_config_id;
    @ApiModelProperty("积分值")
    private String score;
    @ApiModelProperty("记录时间")
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

    public String getPoint_config_id() {
        return point_config_id;
    }

    public void setPoint_config_id(String point_config_id) {
        this.point_config_id = point_config_id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}