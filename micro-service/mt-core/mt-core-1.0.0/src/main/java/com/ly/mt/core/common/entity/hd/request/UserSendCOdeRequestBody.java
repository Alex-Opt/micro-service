package com.ly.mt.core.common.entity.hd.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 下单请求对象
 */
@ApiModel("用户下单发送验证码")
public class UserSendCOdeRequestBody {

    @ApiModelProperty(value = "活动id" ,dataType = "long")
    private Long activityId;

    @ApiModelProperty(value = "门店主键id",dataType = "string")
    private String shopId;

    @ApiModelProperty(value = "活动信息注册主键id" ,dataType = "long")
    private Long shopAttDetailId;

    @ApiModelProperty(value = "手机号",dataType = "string")
    private String phone;


    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Long getShopAttDetailId() {
        return shopAttDetailId;
    }

    public void setShopAttDetailId(Long shopAttDetailId) {
        this.shopAttDetailId = shopAttDetailId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
