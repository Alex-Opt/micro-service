package com.ly.mt.core.base.entity.activity;

import com.ly.mt.core.base.entity.base.BaseEntity;

/** @deprecated */
public class ActivityInfo extends BaseEntity {

    private String name;

    private String startTime;

    private String endTime;

    private String denomination;

    private String discountRate;

    private String couponActivityType;

    private String useChannel;

    private String source;

    private Double startFee;

    private String maxSellNum;

    private String goodsLimitCategory;

    private String status;

    private String description;

    private String creator;

    private String createTime;

    private String oridinaryFee;

    public String getOridinaryFee() {
        return oridinaryFee;
    }

    public void setOridinaryFee(String oridinaryFee) {
        this.oridinaryFee = oridinaryFee;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getCouponActivityType() {
        return couponActivityType;
    }

    public void setCouponActivityType(String couponActivityType) {
        this.couponActivityType = couponActivityType;
    }

    public String getUseChannel() {
        return useChannel;
    }

    public void setUseChannel(String useChannel) {
        this.useChannel = useChannel;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Double getStartFee() {
        return startFee;
    }

    public void setStartFee(Double startFee) {
        this.startFee = startFee;
    }

    public String getMaxSellNum() {
        return maxSellNum;
    }

    public void setMaxSellNum(String maxSellNum) {
        this.maxSellNum = maxSellNum;
    }

    public String getGoodsLimitCategory() {
        return goodsLimitCategory;
    }

    public void setGoodsLimitCategory(String goodsLimitCategory) {
        this.goodsLimitCategory = goodsLimitCategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}