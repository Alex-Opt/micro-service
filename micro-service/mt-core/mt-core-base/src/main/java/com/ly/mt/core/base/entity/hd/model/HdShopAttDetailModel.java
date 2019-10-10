package com.ly.mt.core.base.entity.hd.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 门店活动信息负责人复杂模型
 *
 * 主要保存门店活动信息、负责人手机号、活动海报等信息
 *
 * @author panjingtian
 * @date 2019/6/10 3:19 PM
 *//** @deprecated */
public class HdShopAttDetailModel implements Serializable {

    public HdShopAttDetailModel() {
    }

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
     * 活动负责人
     * @mbggenerated
     */
    private String activityManager;

    /**
     * 活动负责人手机号（作为活动登录账号使用）
     * @mbggenerated
     */
    private String managerPhone;

    /**
     * 门店活动有效性(0失效、1有效)
     * @mbggenerated
     */
    private String shopAttStatus;

    /**
     * 入库时间
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 更新时间
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * 门店活动url
     */
    private String shopActivityUrl;

    /**
     * 短信验证码类型
     * {@link com.ly.mt.core.common.constant.SmsTemplateEnum} dynamicCodeType
     */
    private String dynamicCodeType;

    public String getShopActivityUrl() {
        return shopActivityUrl;
    }

    public void setShopActivityUrl(String shopActivityUrl) {
        this.shopActivityUrl = shopActivityUrl;
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

    public String getShopAttStatus() {
        return shopAttStatus;
    }

    public void setShopAttStatus(String shopAttStatus) {
        this.shopAttStatus = shopAttStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDynamicCodeType() {
        return dynamicCodeType;
    }

    public void setDynamicCodeType(String dynamicCodeType) {
        this.dynamicCodeType = dynamicCodeType;
    }
}
