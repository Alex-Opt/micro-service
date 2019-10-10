package com.ly.mt.core.base.entity.coupon;

import com.ly.mt.core.base.entity.base.BaseEntity;

/** @deprecated */
public class CouponGoods extends BaseEntity {
    private String id;

    private String couponId;

    private String skuId;

    private String createTime;

    @Override
    public String getId() {
        return id;
    }
    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
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