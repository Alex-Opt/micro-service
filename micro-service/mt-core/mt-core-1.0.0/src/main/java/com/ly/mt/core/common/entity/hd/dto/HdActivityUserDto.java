package com.ly.mt.core.common.entity.hd.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @description
 * 活动注册用户dto
 * @author panjingtian
 * @date 2019/6/14 5:39 PM
 */
@ApiModel("活动用户对象")
public class HdActivityUserDto implements Serializable {

    /**
     * 商家活动表id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("门店户活动注册Id")
    private  Long shopAttDetailId;

    /**
     *
     *  1男0女
     */
    @ApiModelProperty("性别 1男0女")
    private String sex;

    /**
     * 电话号码
     */
    @ApiModelProperty("电话号码")
    private String phone;

    /**
     * 年龄
     *
     */
    @ApiModelProperty("年龄")
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public HdActivityUserDto() {
    }

    public Long getShopAttDetailId() {
        return shopAttDetailId;
    }

    public void setShopAttDetailId(Long shopAttDetailId) {
        this.shopAttDetailId = shopAttDetailId;
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

}
