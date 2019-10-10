package com.ly.mt.core.base.entity.hd.vo;


/**
 * @description
 * 活动注册商家信息 vo
 * @author panjingtian
 * @date 2019/6/17 11:06 AM
 *//** @deprecated */
public class HdShopAttDetailVo {

    /**
     * 自增主键
     * @mbggenerated
     */
    private Long id;

    /**
     * 门店主键id
     * @mbggenerated
     */
    private Long shopId;

    /**
     * 活动主键id
     * @mbggenerated
     */
    private Long activityId;

    /**
     *
     * 活动二维码
     * @mbggenerated
     */
    private String imageUrl;

    /**
     * 活动负责人
     * @mbggenerated
     */
    private String activityManager;

    /**
     * 活动负责人手机号（作为活动登录账号使用）
     * @mbggenerated
     */
    private String managerPhone;

    public HdShopAttDetailVo() {
    }

    public HdShopAttDetailVo(Long id, Long shopId, Long activityId, String imageUrl, String activityManager, String managerPhone) {
        this.id = id;
        this.shopId = shopId;
        this.activityId = activityId;
        this.imageUrl = imageUrl;
        this.activityManager = activityManager;
        this.managerPhone = managerPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getActivityManager() {
        return activityManager;
    }

    public void setActivityManager(String activityManager) {
        this.activityManager = activityManager;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }
}

