package com.ly.mt.order.server.trade.decorator;

import com.ly.mt.order.server.activity.service.ActivityInfoService;
import com.ly.mt.order.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.order.server.coupon.service.CouponCodeService;
import com.ly.mt.order.server.coupon.service.CouponGoodsService;
import com.ly.mt.order.server.coupon.service.CouponInfoService;
import com.ly.mt.order.server.goods.GoodsSkuService;
import com.ly.mt.order.server.user.service.UserProfitsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhanglifeng
 * 实现价格计算抽象模型服务的注入问题
 */
@Service
public class DecoratorCommonServiceComponent extends BaseServiceImpl {


    @Resource
    private GoodsSkuService goodsSkuService;

    @Resource
    private CouponCodeService couponCodeService;

    @Resource
    private CouponInfoService couponInfoService;

    @Resource
    private CouponGoodsService couponGoodsService;

    @Resource
    private ActivityInfoService activityInfoService;

    @Resource
    private UserProfitsService userProfitsService;


    public UserProfitsService getUserProfitsService() {
        return userProfitsService;
    }

    public void setUserProfitsService(UserProfitsService userProfitsService) {
        this.userProfitsService = userProfitsService;
    }

    public ActivityInfoService getActivityInfoService() {
        return activityInfoService;
    }

    public CouponCodeService getCouponCodeService() {
        return couponCodeService;
    }

    public void setCouponCodeService(CouponCodeService couponCodeService) {
        this.couponCodeService = couponCodeService;
    }

    public CouponInfoService getCouponInfoService() {
        return couponInfoService;
    }

    public void setCouponInfoService(CouponInfoService couponInfoService) {
        this.couponInfoService = couponInfoService;
    }

    public CouponGoodsService getCouponGoodsService() {
        return couponGoodsService;
    }

    public void setCouponGoodsService(CouponGoodsService couponGoodsService) {
        this.couponGoodsService = couponGoodsService;
    }

    public GoodsSkuService getGoodsSkuService() {
        return goodsSkuService;
    }

    public void setGoodsSkuService(GoodsSkuService goodsSkuService) {
        this.goodsSkuService = goodsSkuService;
    }

    public void setActivityInfoService(ActivityInfoService activityInfoService) {
        this.activityInfoService = activityInfoService;
    }
}
