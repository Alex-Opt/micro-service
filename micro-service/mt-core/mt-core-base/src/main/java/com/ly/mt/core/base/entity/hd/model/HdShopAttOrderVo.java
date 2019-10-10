package com.ly.mt.core.base.entity.hd.model;


import java.math.BigDecimal;
import java.util.List;


/**
 * @author panjingtian
 * @description 订单模型
 * @date 2019/6/17 8:24 PM
 *//** @deprecated */
public class HdShopAttOrderVo implements Cloneable {

    private Long id;

    private String orderId;

    private Long userId;

    private Long shopAttDetailId;

    private Long shopId;

    private BigDecimal totalPrice;

    private String orderStatus;

    private String shippingAddress;

    private Long userAddressId;

    private String getGoodsCode;

    private String orderType;

    /**
     * 买家手机号码
     */
    private String buyerPhone;

    List<HdShopOrderDetailGoodsModel> details;

    public HdShopAttOrderVo() {
    }

    //克隆，防止包装对象时候创建一堆类
    @Override
    protected Object clone() throws CloneNotSupportedException {
        HdShopAttOrderVo vo = (HdShopAttOrderVo)super.clone();
        return vo;
    }


    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShopAttDetailId() {
        return shopAttDetailId;
    }

    public void setShopAttDetailId(Long shopAttDetailId) {
        this.shopAttDetailId = shopAttDetailId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice.setScale(2);
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Long getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(Long userAddressId) {
        this.userAddressId = userAddressId;
    }

    public String getGetGoodsCode() {
        return getGoodsCode;
    }

    public void setGetGoodsCode(String getGoodsCode) {
        this.getGoodsCode = getGoodsCode;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public List<HdShopOrderDetailGoodsModel> getDetails() {
        return details;
    }

    public void setDetails(List<HdShopOrderDetailGoodsModel> details) {
        this.details = details;
    }
}
