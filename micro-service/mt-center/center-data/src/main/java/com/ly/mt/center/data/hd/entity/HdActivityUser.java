package com.ly.mt.center.data.hd.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class HdActivityUser {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("门店活动主键")
    private String hd_shop_att_detail_id;
    @ApiModelProperty("user表id")
    private String user_id;
    @ApiModelProperty("1男 0女")
    private String sex;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("年龄")
    private String age;
    @ApiModelProperty("用户有效状态 0失效 1有效 3已经参与过活动")
    private String user_status;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("更新时间")
    private String update_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHd_shop_att_detail_id() {
        return hd_shop_att_detail_id;
    }

    public void setHd_shop_att_detail_id(String hd_shop_att_detail_id) {
        this.hd_shop_att_detail_id = hd_shop_att_detail_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

}