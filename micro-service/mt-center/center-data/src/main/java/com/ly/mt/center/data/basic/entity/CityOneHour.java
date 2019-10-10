package com.ly.mt.center.data.basic.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CityOneHour {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("base_area区域表编号")
    private String area_id;
    @ApiModelProperty("区域名称")
    private String name;
    @ApiModelProperty("区域代码")
    private String code;
    @ApiModelProperty("状态 0：取消，1：正常")
    private String status;
    @ApiModelProperty("创建时间")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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