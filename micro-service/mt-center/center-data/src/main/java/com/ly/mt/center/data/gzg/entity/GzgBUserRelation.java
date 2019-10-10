package com.ly.mt.center.data.gzg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GzgBUserRelation {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("格子柜ID")
    private String gzg_id;
    @ApiModelProperty("b端用户补货员ID")
    private String b_user_id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGzg_id() {
        return gzg_id;
    }

    public void setGzg_id(String gzg_id) {
        this.gzg_id = gzg_id;
    }

    public String getB_user_id() {
        return b_user_id;
    }

    public void setB_user_id(String b_user_id) {
        this.b_user_id = b_user_id;
    }

}