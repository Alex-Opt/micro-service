package com.ly.mt.core.data.order.entity;

/**
 * order_hc_view
 * 到家B端进货订单视图
 *
 * @author taoye
 */
public class OrderHcView {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 买家Id
     */
    private String buyerId;
    /**
     * 第三方参数CODE
     */
    private String thirdCode;
    /**
     * 买家留言
     */
    private String buyerMemo;
    /**
     * 店铺Id
     */
    private String shopId;
    /**
     * 卖家Id
     */
    private String sellerId;
    /**
     * 订单原始金额
     */
    private String orderOldMoney;
    /**
     * 订单实付金额
     */
    private String orderMoney;
    /**
     * 订单优惠金额
     */
    private String orderDiscountMoney;
    /**
     * 配送方式
     *
     * @see com.ly.mt.core.base.dict.DistributeType
     */
    private String distributionId;
    /**
     * 快递公司code
     */
    private String logisticsCode;
    /**
     * 快递公司单号
     */
    private String gyLogisticsCode;
    /**
     * 运费
     */
    private String freight;
    /**
     * 订单状态
     *
     * @see com.ly.mt.core.base.dict.OrderStatus
     */
    private String orderStatus;
    /**
     * 订单来源
     *
     * @see com.ly.mt.core.base.dict.OrderSource
     */
    private String orderSource;
    /**
     * 订单取消时间
     */
    private String cancleTime;
    /**
     * 取消订单
     */
    private String orderYn;
    /**
     * 是否退货
     */
    private String isRefund;
    /**
     * 申请退货时间
     */
    private String refundTime;
    /**
     * 是否同意将一小时达订单变成普通次日达订单
     */
    private String isAgree;
    /**
     * 客户同意时间
     */
    private String agreeTime;
    /**
     * 支付方式
     */
    private String paymentType;
    /**
     * 订单推送的状态
     */
    private String pushStatus;
    /**
     * 订单类型
     *
     * @see com.ly.mt.core.base.dict.OrderType
     */
    private String orderType;
    /**
     * 用户地址Id
     */
    private String addressId;
    /**
     * 自动收货时间
     */
    private String autoReceiveTime;
    /**
     * 自动取消时间
     */
    private String autoCancelTime;
    /**
     * 订单快照文件在OSS上保存的Key
     */
    private String orderSnapshootKey;
    /**
     * 订单锁定
     */
    private String locked;
    /**
     * 锁定时间
     */
    private String lockTime;
    /**
     * 订单预计发货时间
     */
    private String deliveryTime;
    /**
     * 下单时间
     */
    private String createTime;
    /**
     * 订单支付时间
     */
    private String payTime;
    /**
     * 订单完成时间
     */
    private String orderFinishTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 买家姓名
     */
    private String buyerName;
    /**
     * 商品名称
     */
    private String goodsNames;
    /**
     * 店铺名称
     */
    private String shopName;
    /**
     * 卖家手机号
     */
    private String sellerMobile;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public String getBuyerMemo() {
        return buyerMemo;
    }

    public void setBuyerMemo(String buyerMemo) {
        this.buyerMemo = buyerMemo;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getOrderOldMoney() {
        return orderOldMoney;
    }

    public void setOrderOldMoney(String orderOldMoney) {
        this.orderOldMoney = orderOldMoney;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getOrderDiscountMoney() {
        return orderDiscountMoney;
    }

    public void setOrderDiscountMoney(String orderDiscountMoney) {
        this.orderDiscountMoney = orderDiscountMoney;
    }

    public String getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(String distributionId) {
        this.distributionId = distributionId;
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

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getCancleTime() {
        return cancleTime;
    }

    public void setCancleTime(String cancleTime) {
        this.cancleTime = cancleTime;
    }

    public String getOrderYn() {
        return orderYn;
    }

    public void setOrderYn(String orderYn) {
        this.orderYn = orderYn;
    }

    public String getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(String isRefund) {
        this.isRefund = isRefund;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    public String getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(String isAgree) {
        this.isAgree = isAgree;
    }

    public String getAgreeTime() {
        return agreeTime;
    }

    public void setAgreeTime(String agreeTime) {
        this.agreeTime = agreeTime;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(String pushStatus) {
        this.pushStatus = pushStatus;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAutoReceiveTime() {
        return autoReceiveTime;
    }

    public void setAutoReceiveTime(String autoReceiveTime) {
        this.autoReceiveTime = autoReceiveTime;
    }

    public String getAutoCancelTime() {
        return autoCancelTime;
    }

    public void setAutoCancelTime(String autoCancelTime) {
        this.autoCancelTime = autoCancelTime;
    }

    public String getOrderSnapshootKey() {
        return orderSnapshootKey;
    }

    public void setOrderSnapshootKey(String orderSnapshootKey) {
        this.orderSnapshootKey = orderSnapshootKey;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getLockTime() {
        return lockTime;
    }

    public void setLockTime(String lockTime) {
        this.lockTime = lockTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(String orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getGoodsNames() {
        return goodsNames;
    }

    public void setGoodsNames(String goodsNames) {
        this.goodsNames = goodsNames;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSellerMobile() {
        return sellerMobile;
    }

    public void setSellerMobile(String sellerMobile) {
        this.sellerMobile = sellerMobile;
    }
}