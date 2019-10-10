package com.ly.mt.center.data.point.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PointConfig {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("规则名称")
    private String title;
    @ApiModelProperty("规则标识名")
    private String name;
    @ApiModelProperty("积分值")
    private String score;
    @ApiModelProperty("规则类型")
    private String point_type;
    @ApiModelProperty("规则状态 1：上架，2：下架")
    private String point_status;
    @ApiModelProperty("有效期开始时间")
    private String start_time;
    @ApiModelProperty("有效期结束时间")
    private String end_time;
    @ApiModelProperty("创建时间")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPoint_type() {
        return point_type;
    }

    public void setPoint_type(String point_type) {
        this.point_type = point_type;
    }

    public String getPoint_status() {
        return point_status;
    }

    public void setPoint_status(String point_status) {
        this.point_status = point_status;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}