package com.ly.mt.core.data.shop.entity;

/**
 * shop_purchases
 *
 * @author taoye
 */
public class ShopPurchases {
    /**
     * 主键
     */
    private String id;
    /**
     * 买家ID
     */
    private String userId;
    /**
     * 卖家ID
     */
    private String sellerId;
    /**
     * 店铺ID
     */
    private String shopId;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 省编码
     */
    private String provinceCode;
    /**
     * 市编码
     */
    private String cityCode;
    /**
     * 区县编码
     */
    private String districtCode;
    /**
     * 地址
     */
    private String address;
    /**
     * 收货地址ID
     */
    private String addressId;
    /**
     * 详细地址
     */
    private String fullAddress;
    /**
     * 优惠券编号
     */
    private String couponId;
    /**
     * 配送方式
     *
     * @see com.ly.mt.core.base.dict.DistributeType
     */
    private String deliveryType;
    /**
     * 配送费
     */
    private String deliveryFee;
    /**
     * 订单金额
     */
    private String amount;
    /**
     * 优惠金额
     */
    private String discount;
    /**
     * 实际金额
     */
    private String actualAmount;
    /**
     * 快递公司代码
     */
    private String logisticsCode;
    /**
     * 快递单号
     */
    private String gyLogisticsCode;
    /**
     * 状态
     *
     * @see com.ly.mt.core.base.dict.OrderStatus
     */
    private String status;
    /**
     * 管易订单推送状态
     */
    private String pushStatus;
    /**
     * 支付方式
     *
     * @see com.ly.mt.core.base.dict.PaymentType
     */
    private String payedModel;
    /**
     * 支付订单号
     */
    private String transactionId;
    /**
     * 支付时间
     */
    private String payedTime;
    /**
     * 发货时间
     */
    private String sendedTime;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String modifyTime;
    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
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

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getGyLogisticsCode() {
        return gyLogisticsCode;
    }

    public void setGyLogisticsCode(String gyLogisticsCode) {
        this.gyLogisticsCode = gyLogisticsCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(String pushStatus) {
        this.pushStatus = pushStatus;
    }

    public String getPayedModel() {
        return payedModel;
    }

    public void setPayedModel(String payedModel) {
        this.payedModel = payedModel;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPayedTime() {
        return payedTime;
    }

    public void setPayedTime(String payedTime) {
        this.payedTime = payedTime;
    }

    public String getSendedTime() {
        return sendedTime;
    }

    public void setSendedTime(String sendedTime) {
        this.sendedTime = sendedTime;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}