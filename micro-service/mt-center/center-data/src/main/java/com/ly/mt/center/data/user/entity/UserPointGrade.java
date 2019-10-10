package com.ly.mt.center.data.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserPointGrade {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("用户Id")
    private String user_id;
    @ApiModelProperty("用户积分")
    private String user_score;
    @ApiModelProperty("积分等级Id （积分等级配置表）")
    private String point_grade_id;
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

    public String getUser_score() {
        return user_score;
    }

    public void setUser_score(String user_score) {
        this.user_score = user_score;
    }

    public String getPoint_grade_id() {
        return point_grade_id;
    }

    public void setPoint_grade_id(String point_grade_id) {
        this.point_grade_id = point_grade_id;
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