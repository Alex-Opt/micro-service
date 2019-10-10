package com.ly.mt.blue.tooth.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description user
 * @Author whl
 */
@ApiModel(value="用户认证结果对象")
public class BlueToothUserCertficationVo {
    /**
     * @Description
     */
    @ApiModelProperty(value = "认证状态 1 已认证  2 未成年", required = true)
    private String  status;
    @ApiModelProperty(value = "优惠卷Id", required = true)
    private String couponId;
    /**
     * @Description
     */
    @ApiModelProperty(value = "优惠卷名称", required = true)
    private String  couponName;
    /**
     * @Description
     */
    @ApiModelProperty(value = "优惠卷面额", required = true)
    private String  denomination;
    /**
     * @Description
     */
    @ApiModelProperty(value = "优惠卷有效期 示例:2019-01-01 09:00:00", required = true)
    private String  validityDay;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getValidityDay() {
        return validityDay;
    }

    public void setValidityDay(String validityDay) {
        this.validityDay = validityDay;
    }
}