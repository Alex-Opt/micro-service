package com.ly.mt.center.data.gzg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgHotelUserRelation {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("用户ID")
    private String user_id;
    @ApiModelProperty("酒店ID")
    private String hotel_id;
    @ApiModelProperty("0运营1酒店管理2补货员")
    private String tyep;
    @ApiModelProperty("")
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

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getTyep() {
        return tyep;
    }

    public void setTyep(String tyep) {
        this.tyep = tyep;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}