package com.ly.mt.center.data.activity.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ActivityUserGradeDetail {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("活动Id")
    private String activity_id;
    @ApiModelProperty("用户等级id")
    private String grade_id;
    @ApiModelProperty("用户等级名称")
    private String grade_name;
    @ApiModelProperty("用户使用促销活动购买商品的spuId")
    private String spu_id;
    @ApiModelProperty("用户使用促销活动购买商品的数量")
    private String spu_num;
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

    public String getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(String grade_id) {
        this.grade_id = grade_id;
    }

    public String getGrade_name() {
        return grade_name;
    }

    public void setGrade_name(String grade_name) {
        this.grade_name = grade_name;
    }

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }

    public String getSpu_num() {
        return spu_num;
    }

    public void setSpu_num(String spu_num) {
        this.spu_num = spu_num;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}