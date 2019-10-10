package com.ly.mt.center.data.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserCouponeCodeDel {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("用户Id")
    private String user_id;
    @ApiModelProperty("优惠Id")
    private String coupon_id;
    @ApiModelProperty("优惠码")
    private String coupon_code;
    @ApiModelProperty("数量")
    private String num;
    @ApiModelProperty("来源 1：领取，2：积分兑换")
    private String source;
    @ApiModelProperty("领取时间")
    private String get_time;
    @ApiModelProperty("创建时间")
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

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getGet_time() {
        return get_time;
    }

    public void setGet_time(String get_time) {
        this.get_time = get_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}