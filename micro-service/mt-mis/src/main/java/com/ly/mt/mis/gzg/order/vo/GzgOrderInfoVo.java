package com.ly.mt.mis.gzg.order.vo;

import java.util.List;

public class GzgOrderInfoVo {
    /**
     * 订单信息
     */
    private String id;
    private String createTime;
    private String orderStatusName;
    /**
     * 商品信息
     */
    private List<GzgOrderInfoGoodsVo> goods;
    /**
     * 格子柜信息
     */
    private String code;
    private String name;
    private String address;
    private String bdName;
    private String bdMobile;
    private String hotelAdminMobile;
    private String planName;
    /**
     * 支付信息
     */
    private String paymentTypeName;
    private String paymentNo;
    private String oldMoney;
    private String payMoney;
    private String payTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public List<GzgOrderInfoGoodsVo> getGoods() {
        return goods;
    }

    public void setGoods(List<GzgOrderInfoGoodsVo> goods) {
        this.goods = goods;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBdName() {
        return bdName;
    }

    public void setBdName(String bdName) {
        this.bdName = bdName;
    }

    public String getBdMobile() {
        return bdMobile;
    }

    public void setBdMobile(String bdMobile) {
        this.bdMobile = bdMobile;
    }

    public String getHotelAdminMobile() {
        return hotelAdminMobile;
    }

    public void setHotelAdminMobile(String hotelAdminMobile) {
        this.hotelAdminMobile = hotelAdminMobile;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getOldMoney() {
        return oldMoney;
    }

    public void setOldMoney(String oldMoney) {
        this.oldMoney = oldMoney;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
}