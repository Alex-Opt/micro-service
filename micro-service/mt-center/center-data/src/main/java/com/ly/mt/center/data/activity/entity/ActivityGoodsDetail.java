package com.ly.mt.center.data.activity.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ActivityGoodsDetail {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("活动Id")
    private String activity_id;
    @ApiModelProperty("spu_id")
    private String spu_id;
    @ApiModelProperty("spu名称")
    private String spu_name;
    @ApiModelProperty("spu限制最大销售总量")
    private String max_sell_num;
    @ApiModelProperty("创建时间")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }

    public String getSpu_name() {
        return spu_name;
    }

    public void setSpu_name(String spu_name) {
        this.spu_name = spu_name;
    }

    public String getMax_sell_num() {
        return max_sell_num;
    }

    public void setMax_sell_num(String max_sell_num) {
        this.max_sell_num = max_sell_num;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}