package com.ly.mt.core.common.entity.hd.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("发送验证码请求对象")
public class ShopSendRegistCodeRequestBody {
/*    {
        "phone": "17600241924",
            "dynamicCodeType": "0",
            "flag": "0",
            "activityId": 546564126
    }
    */

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("验证码类型")
    private String dynamicCodeType;

    @ApiModelProperty("是否可以重复注册，0不可以 1可以")
    private String flag;

    @ApiModelProperty("活动id")
    private Long activityId;

    @ApiModelProperty("验证码")
    private String dynamicCode;


    public ShopSendRegistCodeRequestBody() {
    }

    public String getDynamicCode() {
        return dynamicCode;
    }

    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDynamicCodeType() {
        return dynamicCodeType;
    }

    public void setDynamicCodeType(String dynamicCodeType) {
        this.dynamicCodeType = dynamicCodeType;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
