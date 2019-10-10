package com.ly.mt.center.data.shop.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShopPurchasesDiscount {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("店铺编号")
    private String shop_id;
    @ApiModelProperty("用户编号")
    private String user_id;
    @ApiModelProperty("进货单编号")
    private String purchases_id;
    @ApiModelProperty("优惠券Id")
    private String shop_coupon_id;
    @ApiModelProperty("优惠类型 1：阶梯价优惠，2：会员价优惠，3：优惠券优惠")
    private String discount_type;
    @ApiModelProperty("优惠金额")
    private String discount_price;
    @ApiModelProperty("状态 1：正常，2：异常（退货或其他情况）")
    private String status;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("修改时间")
    private String modify_time;
    @ApiModelProperty("备注")
    private String remarks;
    @ApiModelProperty("优惠折扣")
    private String discount_rate;


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

    public String getPurchases_id() {
        return purchases_id;
    }

    public void setPurchases_id(String purchases_id) {
        this.purchases_id = purchases_id;
    }

    public String getShop_coupon_id() {
        return shop_coupon_id;
    }

    public void setShop_coupon_id(String shop_coupon_id) {
        this.shop_coupon_id = shop_coupon_id;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(String discount_rate) {
        this.discount_rate = discount_rate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}