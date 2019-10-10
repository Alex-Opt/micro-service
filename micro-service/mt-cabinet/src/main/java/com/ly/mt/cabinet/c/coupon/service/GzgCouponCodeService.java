package com.ly.mt.cabinet.c.coupon.service;

import com.ly.mt.cabinet.c.coupon.entity.CouponInfo;
import com.ly.mt.cabinet.c.coupon.entity.GzgCouponTypeEnum;
import com.ly.mt.cabinet.c.coupon.entity.GzgCouponCode;
import com.ly.mt.cabinet.c.user.entity.GzgUserRegisterVo;
import com.ly.mt.core.base.entity.ResponseJson;

import java.util.List;

/**
 * 优惠券码表
 *
 * @author zhanglifeng
 * @date 2019-05-24尚未使用的优惠券集合
 */
public interface GzgCouponCodeService {

    /**
     * 用户领取优惠券
     *
     * @param user
     * @param gzgCouponTypeEnum
     * @return
     */
    void saveGzgCouponCode(GzgCouponTypeEnum gzgCouponTypeEnum, GzgUserRegisterVo user) throws Exception;


    /**
     * 判断用户是否领取某个类型的优惠券
     * @param gzgCouponTypeEnum
     * @param userId
     * @return
     * @throws Exception
     */
    public Boolean isReceiveGzgCouponInfo(GzgCouponTypeEnum gzgCouponTypeEnum, String userId) throws Exception ;

    /**
     * 根据用户id查询领取,
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<GzgCouponCode> selectNotUsedCouponByUserId(String userId) throws Exception;


    /**
     * 根据用户id查询领取,
     *
     * @param userId
     * @return
     * @throws Exception
     */
    String isUsedCouponByUserIdAndCouponId(String userId,String CouponId) throws Exception;


    /**
     * 根据用户id查询所有优惠券信息,
     *
     * @param userId
     * @return
     * @throws Exception
     */
    ResponseJson selectAllCouponByUserId(String userId) throws Exception;



    CouponInfo getGzgCouponInfoById(String couponId) throws Exception;
}
