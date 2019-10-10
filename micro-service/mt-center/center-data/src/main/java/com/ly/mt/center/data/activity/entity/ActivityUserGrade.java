package com.ly.mt.center.data.activity.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ActivityUserGrade {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("活动Id")
    private String activity_id;
    @ApiModelProperty("限制类型：-1-无限制，1-有最低等级限制，2-有最高等级限制，3有最低，最高等级限制")
    private String limit_type;
    @ApiModelProperty("允许使用促销活动的用户最低等级id(针对limit_type=1,limit_type=3时有值)")
    private String grade_min_id;
    @ApiModelProperty("允许使用促销活动的用户最低等级名称(针对limit_type=1,limit_type=3时有值)")
    private String grade_min_name;
    @ApiModelProperty("允许使用促销活动的用户最高等级id(针对limit_type=2,limit_type=3时有值)")
    private String grade_max_id;
    @ApiModelProperty("允许使用促销活动的用户最高等级名称(针对limit_type=2,limit_type=3时有值)")
    private String grade_max_name;
    @ApiModelProperty("等级用户（群体）单次购买spu商品数量允许的最大值（ -1-无限制 。正常多少表示限制多少）")
    private String user_limit_count;
    @ApiModelProperty("促销活动允许用户所属等级（群体）使用总次数上限（ -1-无限制 。正常多少表示限制多少）")
    private String total_count;
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

    public String getLimit_type() {
        return limit_type;
    }

    public void setLimit_type(String limit_type) {
        this.limit_type = limit_type;
    }

    public String getGrade_min_id() {
        return grade_min_id;
    }

    public void setGrade_min_id(String grade_min_id) {
        this.grade_min_id = grade_min_id;
    }

    public String getGrade_min_name() {
        return grade_min_name;
    }

    public void setGrade_min_name(String grade_min_name) {
        this.grade_min_name = grade_min_name;
    }

    public String getGrade_max_id() {
        return grade_max_id;
    }

    public void setGrade_max_id(String grade_max_id) {
        this.grade_max_id = grade_max_id;
    }

    public String getGrade_max_name() {
        return grade_max_name;
    }

    public void setGrade_max_name(String grade_max_name) {
        this.grade_max_name = grade_max_name;
    }

    public String getUser_limit_count() {
        return user_limit_count;
    }

    public void setUser_limit_count(String user_limit_count) {
        this.user_limit_count = user_limit_count;
    }

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}