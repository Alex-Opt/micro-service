package com.ly.mt.core.common.entity.goods;

import com.ly.mt.core.common.entity.activity.ActivityInfo;
import com.ly.mt.core.common.entity.coupon.CouponInfo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 商品spu VO类
 * @Author
 */
public class GoodsSpuInfoVo extends GoodsSpuInfo {

    private List<Map<String, Object>>  attr; //商品spu属性信息

    private List<GoodsSpuPicture> pictureList;//商品spu图片信息

    private List<ActivityInfo> activityList;//商品活动数据

    private List<CouponInfo> couponList;//商品优惠券数据

    public List<Map<String, Object>> getAttr() {
        return attr;
    }

    public void setAttr(List<Map<String, Object>> attr) {
        this.attr = attr;
    }

    public List<GoodsSpuPicture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<GoodsSpuPicture> pictureList) {
        this.pictureList = pictureList;
    }

    public List<ActivityInfo> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityInfo> activityList) {
        this.activityList = activityList;
    }

    public List<CouponInfo> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<CouponInfo> couponList) {
        this.couponList = couponList;
    }
}