package com.ly.mt.core.common.entity.hd.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("门店注册信息")
public class HdShopAttDetailModelRequestBody {

    /**
     * 门店主键id
     * @mbggenerated
     */
    @ApiModelProperty("门店id")
    private String shopId;

    /**
     * 活动主键id
     * @mbggenerated
     */
    @ApiModelProperty("活动id")
    private Long activityId;


    /**
     * 活动负责人
     * @mbggenerated
     */
    @ApiModelProperty("活动负责人")
    private String activityManager;

    /**
     * 活动负责人手机号（作为活动登录账号使用）
     * @mbggenerated
     */
    @ApiModelProperty("负责人手机号")
    private String managerPhone;

    /**
     * 门店活动有效性(0失效、1有效)
     * @mbggenerated
     */
    @ApiModelProperty("门店活动有效性(0失效、1有效)")
    private String shopAttStatus;

    /**
     * 入库时间
     * @mbggenerated
     */
    @ApiModelProperty("入库时间")
    private Date createTime;

    /**
     * 更新时间
     * @mbggenerated
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 门店活动url
     */
    @ApiModelProperty("活动url前缀")
    private String shopActivityUrl;

    /**
     * 短信验证码类型
     * {@link com.ly.mt.core.common.constant.SmsTemplateEnum} dynamicCodeType
     */
    @ApiModelProperty("短信验证码")
    private String dynamicCodeType;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
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

    public String getShopActivityUrl() {
        return shopActivityUrl;
    }

    public void setShopActivityUrl(String shopActivityUrl) {
        this.shopActivityUrl = shopActivityUrl;
    }

    public String getDynamicCodeType() {
        return dynamicCodeType;
    }

    public void setDynamicCodeType(String dynamicCodeType) {
        this.dynamicCodeType = dynamicCodeType;
    }
}
