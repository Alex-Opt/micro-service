package com.ly.mt.center.data.shop.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShopPurchases {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("买家Id")
    private String user_id;
    @ApiModelProperty("卖家id")
    private String seller_id;
    @ApiModelProperty("店铺Id")
    private String shop_id;
    @ApiModelProperty("收货人")
    private String consignee;
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("省编码")
    private String province_code;
    @ApiModelProperty("城市编码")
    private String city_code;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("详细地址")
    private String full_address;
    @ApiModelProperty("优惠券编号")
    private String coupon_id;
    @ApiModelProperty("配送方式")
    private String delivery_type;
    @ApiModelProperty("配送费")
    private String delivery_fee;
    @ApiModelProperty("订单金额")
    private String amount;
    @ApiModelProperty("优惠金额")
    private String discount;
    @ApiModelProperty("实际金额")
    private String actual_amount;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("状态10:待付款 15:已取消 20:待配送 25:部分发货 30:待收货 99:完成")
    private String status;
    @ApiModelProperty("支付方式 1 网银，2 微信，3 支付宝，4 其他，5 信用，6 其他，7 线下方式")
    private String payed_model;
    @ApiModelProperty("支付订单号")
    private String transaction_id;
    @ApiModelProperty("支付时间")
    private String payed_time;
    @ApiModelProperty("发货时间")
    private String sended_time;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("修改时间")
    private String modify_time;
    @ApiModelProperty("快递公司代码")
    private String logistics_code;
    @ApiModelProperty("快递单号")
    private String gy_logistics_code;
    @ApiModelProperty("区县code")
    private String district_code;
    @ApiModelProperty("收货地址id")
    private String address_id;
    @ApiModelProperty("管易订单推送状态")
    private String push_status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(String delivery_type) {
        this.delivery_type = delivery_type;
    }

    public String getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(String delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getActual_amount() {
        return actual_amount;
    }

    public void setActual_amount(String actual_amount) {
        this.actual_amount = actual_amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayed_model() {
        return payed_model;
    }

    public void setPayed_model(String payed_model) {
        this.payed_model = payed_model;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getPayed_time() {
        return payed_time;
    }

    public void setPayed_time(String payed_time) {
        this.payed_time = payed_time;
    }

    public String getSended_time() {
        return sended_time;
    }

    public void setSended_time(String sended_time) {
        this.sended_time = sended_time;
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

    public String getLogistics_code() {
        return logistics_code;
    }

    public void setLogistics_code(String logistics_code) {
        this.logistics_code = logistics_code;
    }

    public String getGy_logistics_code() {
        return gy_logistics_code;
    }

    public void setGy_logistics_code(String gy_logistics_code) {
        this.gy_logistics_code = gy_logistics_code;
    }

    public String getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getPush_status() {
        return push_status;
    }

    public void setPush_status(String push_status) {
        this.push_status = push_status;
    }
}