package com.ly.mt.core.common.entity.trade;

import com.ly.mt.core.common.entity.activity.ActivityUserGradeDetail;
import com.ly.mt.core.common.entity.coupon.CouponModel;
import com.ly.mt.core.common.entity.activity.ActivityModel;
import com.ly.mt.core.common.entity.goods.GoodsSkuModel;

import java.util.List;

/**
 * @author zhanglifeng
 * @description 下单页面展示信息的模型，下单后前端的入参模型 不需要extends BaseEntity
 * @date 2019-05-23
 */
public class OrderModel {

    /**
     * 购买的商品信息模型的集合
     */
    private List<GoodsSkuModel> itemList;


    /**
     * 可使用的优惠券实体模型的集合
     */
    private List<CouponModel> couponList;

    /**
     * 订单优惠信息
     */
    private List<TradeOrderCoupon> traderOrderCouponList;

    /**
     * 可用促销活动实体模型的集合
     */
    private List<ActivityModel> activityList;

    /**
     * 参与促销优惠活动的等级用户使用明细表
     */
    private List<ActivityUserGradeDetail> activityUserGradeDetailList;

    /**
     * 主订单信息实体
     */
    private OrderVo orderVo;

    /**
     * 是否是首单：true-是，false-不是
     */
    private String firstOrder;

    public List<GoodsSkuModel> getItemList() {
        return itemList;
    }

    public void setItemList(List<GoodsSkuModel> itemList) {
        this.itemList = itemList;
    }

    public List<CouponModel> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<CouponModel> couponList) {
        this.couponList = couponList;
    }

    public List<ActivityModel> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityModel> activityList) {
        this.activityList = activityList;
    }

    public OrderVo getOrderVo() {
        return orderVo;
    }

    public void setOrderVo(OrderVo orderVo) {
        this.orderVo = orderVo;
    }

    public List<TradeOrderCoupon> getTraderOrderCouponList() {
        return traderOrderCouponList;
    }

    public void setTraderOrderCouponList(List<TradeOrderCoupon> traderOrderCouponList) {
        this.traderOrderCouponList = traderOrderCouponList;
    }

    public List<ActivityUserGradeDetail> getActivityUserGradeDetailList() {
        return activityUserGradeDetailList;
    }

    public void setActivityUserGradeDetailList(List<ActivityUserGradeDetail> activityUserGradeDetailList) {
        this.activityUserGradeDetailList = activityUserGradeDetailList;
    }

    public String getFirstOrder() {
        return firstOrder;
    }

    public void setFirstOrder(String firstOrder) {
        this.firstOrder = firstOrder;
    }
}
