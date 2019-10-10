package com.ly.mt.center.data.gzg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgCity {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("格子柜所在城市名称")
    private String gzg_city;
    @ApiModelProperty("城市状态，0停用，1正常")
    private String gzg_city_state;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGzg_city() {
        return gzg_city;
    }

    public void setGzg_city(String gzg_city) {
        this.gzg_city = gzg_city;
    }

    public String getGzg_city_state() {
        return gzg_city_state;
    }

    public void setGzg_city_state(String gzg_city_state) {
        this.gzg_city_state = gzg_city_state;
    }

}