package com.ly.mt.center.data.punch.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PunchCardPoint {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("积分规则Id")
    private String point_config_id;
    @ApiModelProperty("状态 1：有效，2：无效")
    private String status;
    @ApiModelProperty("创建时间")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoint_config_id() {
        return point_config_id;
    }

    public void setPoint_config_id(String point_config_id) {
        this.point_config_id = point_config_id;
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

}