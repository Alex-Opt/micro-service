package com.ly.mt.center.data.shop.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShopExclusiveDiscount {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("店铺id")
    private String shop_id;
    @ApiModelProperty("用户id")
    private String user_id;
    @ApiModelProperty("类型（1:折扣优惠,2:现金优惠）")
    private String category;
    @ApiModelProperty("折扣")
    private String discount_rate;
    @ApiModelProperty("优惠金额")
    private String discount_cash;
    @ApiModelProperty("优惠金额限制（满减）")
    private String discount_cash_limit;
    @ApiModelProperty("是否启用(0:否,1:是)")
    private String is_enable;
    @ApiModelProperty("审核人id")
    private String audit_id;
    @ApiModelProperty("审核时间")
    private String audit_time;
    @ApiModelProperty("创建人id")
    private String create_id;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("修改时间")
    private String modify_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(String discount_rate) {
        this.discount_rate = discount_rate;
    }

    public String getDiscount_cash() {
        return discount_cash;
    }

    public void setDiscount_cash(String discount_cash) {
        this.discount_cash = discount_cash;
    }

    public String getDiscount_cash_limit() {
        return discount_cash_limit;
    }

    public void setDiscount_cash_limit(String discount_cash_limit) {
        this.discount_cash_limit = discount_cash_limit;
    }

    public String getIs_enable() {
        return is_enable;
    }

    public void setIs_enable(String is_enable) {
        this.is_enable = is_enable;
    }

    public String getAudit_id() {
        return audit_id;
    }

    public void setAudit_id(String audit_id) {
        this.audit_id = audit_id;
    }

    public String getAudit_time() {
        return audit_time;
    }

    public void setAudit_time(String audit_time) {
        this.audit_time = audit_time;
    }

    public String getCreate_id() {
        return create_id;
    }

    public void setCreate_id(String create_id) {
        this.create_id = create_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }
}