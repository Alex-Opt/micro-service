package com.ly.mt.core.base.entity.trade;

//退货记录显示Vo
/** @deprecated */
public class TradeOrderRefundInfoVo {
    /*退货单id*/
    private  String refundId;
    /** 订单Id */
    private String orderId;
    /** 买家Id */
    private String buyerId;
    /** 退款申请的状态 10：申请状态，20：同意退货，30：拒绝退货，50：买家发货，60：平台收货，99：退货完成 */
    private String refundStatus;
    /** 退货问题描述 */
    private String refundDesc;
    /** 退货原因 */
    private String refundReason;
    /** 退款金额（分） */
    private Double refundPrice;
    /** 退款运费金额（分） */
    private Double refundFreightPrice;
    /** 扣减优惠金额（分） */
    private Double subtractCouponPrice;
    /** 退货地址 */
    private String retundAddress;
    /** 退货类型 1：整单退货，2：部分退货 */
    private String refundType;
    /** 收货人名字 */
    private String consignee;
    /** 收货人电话 */
    private String phone;

    /** 是否取消退货*/
    private String status;

    //sku编码
    private String skuId;
    //退货数量
    private String refundNum;
    //sku名称
    private String  skuName;
    //商品名称
    private String spuName;
    //商品图片
    private String pictureUrl;


    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getRefundDesc() {
        return refundDesc;
    }

    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public Double getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(Double refundPrice) {
        this.refundPrice = refundPrice;
    }

    public Double getRefundFreightPrice() {
        return refundFreightPrice;
    }

    public void setRefundFreightPrice(Double refundFreightPrice) {
        this.refundFreightPrice = refundFreightPrice;
    }

    public Double getSubtractCouponPrice() {
        return subtractCouponPrice;
    }

    public void setSubtractCouponPrice(Double subtractCouponPrice) {
        this.subtractCouponPrice = subtractCouponPrice;
    }

    public String getRetundAddress() {
        return retundAddress;
    }

    public void setRetundAddress(String retundAddress) {
        this.retundAddress = retundAddress;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(String refundNum) {
        this.refundNum = refundNum;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
