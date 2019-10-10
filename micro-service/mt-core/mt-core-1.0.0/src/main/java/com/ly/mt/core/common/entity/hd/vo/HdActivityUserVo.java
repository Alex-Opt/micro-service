package com.ly.mt.core.common.entity.hd.vo;


/**
 * @description
 *
 * 活动用户vo
 * @author panjingtian
 * @date 2019/6/17 1:02 PM
 */
public class HdActivityUserVo {

    private Long id;

    private Long hdShopAttDetailId;

    private String sex;

    private String phone;

    private Integer age;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHdShopAttDetailId() {
        return hdShopAttDetailId;
    }

    public void setHdShopAttDetailId(Long hdShopAttDetailId) {
        this.hdShopAttDetailId = hdShopAttDetailId;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public HdActivityUserVo() {
    }

    public HdActivityUserVo(Long id, Long hdShopAttDetailId, String sex, String phone, Integer age) {
        this.id = id;
        this.hdShopAttDetailId = hdShopAttDetailId;
        this.sex = sex;
        this.phone = phone;
        this.age = age;
    }
}
