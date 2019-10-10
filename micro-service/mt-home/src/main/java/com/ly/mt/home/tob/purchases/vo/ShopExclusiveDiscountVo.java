package com.ly.mt.home.tob.purchases.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 专属优惠
 *
 * @author: linan
 * @date: 2019/9/10
 **/
@ApiModel
public class ShopExclusiveDiscountVo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("店铺id")
    private String shopId;
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("类型（1:折扣优惠,2:现金优惠）")
    private String category;
    @ApiModelProperty("折扣")
    private String discountRate;
    @ApiModelProperty("优惠金额")
    private String discountCash;
    @ApiModelProperty("优惠金额限制（满减）")
    private String discountCashLimit;
    @ApiModelProperty("是否启用(0:否,1:是)")
    private String isEnable;
    @ApiModelProperty("审核人id")
    private String auditId;
    @ApiModelProperty("审核时间")
    private String auditTime;
    @ApiModelProperty("创建人id")
    private String createId;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("修改时间")
    private String modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getDiscountCash() {
        return discountCash;
    }

    public void setDiscountCash(String discountCash) {
        this.discountCash = discountCash;
    }

    public String getDiscountCashLimit() {
        return discountCashLimit;
    }

    public void setDiscountCashLimit(String discountCashLimit) {
        this.discountCashLimit = discountCashLimit;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}