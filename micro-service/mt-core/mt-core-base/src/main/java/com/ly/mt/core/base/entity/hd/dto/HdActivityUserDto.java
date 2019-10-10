package com.ly.mt.core.base.entity.hd.dto;


/**
 * @description
 * 活动注册用户dto
 * @author panjingtian
 * @date 2019/6/14 5:39 PM
 *//** @deprecated */
public class HdActivityUserDto  {

    /**
     * 商家活动表id
     */
    private  Long shopAttDetailId;

    /**
     *
     *  1男0女
     */
    private String sex;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 年龄
     *
     */

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
