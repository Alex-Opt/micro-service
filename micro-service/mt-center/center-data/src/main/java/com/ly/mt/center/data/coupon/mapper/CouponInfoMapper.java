package com.ly.mt.center.data.coupon.mapper;

import com.ly.mt.center.data.coupon.entity.CouponInfo;
import com.ly.mt.center.data.coupon.entity.CouponModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CouponInfoMapper {
    /**
     * @Description 保存CouponInfo
     * @Author taoye
     */
    void insertCouponInfo(CouponInfo couponInfo);

    /**
     * @Description 删除CouponInfo
     * @Author taoye
     */
    void deleteCouponInfo(CouponInfo couponInfo);

    /**
     * @Description 更新CouponInfo
     * @Author taoye
     */
    int updateCouponInfo(CouponInfo couponInfo);

    /**
     * @Description 查询CouponInfo
     * @Author taoye
     */
    CouponInfo getCouponInfo(CouponInfo couponInfo);

    /**
     * 根据spuId查询优惠券数据
     * @param spuId
     * @return
     */
    List<CouponInfo> getCouponInfoBySpuId(String spuId);

    /**
     * 查询用户优惠券信息
     * @param userId
     * @Param nowTime
     * @return
     */
    List<CouponInfo> getCouponInfoByUserId(@Param("userId") String userId, @Param("nowTime") String nowTime);

    /**
     * 获取新人优惠券-系统发放
     * @param nowTime
     * @return
     */
    List<CouponInfo> getNewUserCoupons(@Param("nowTime") String nowTime);

    /**
     * 获取新人优惠券大礼包-到家C端
     * @param nowTime
     * @return
     */
    List<CouponInfo> getNewUserCouponsSpree(@Param("nowTime") String nowTime);

    /**
     * 获取商家优惠券-到家B端
     * @param couponModel
     * @return
     */
    List<CouponModel> getCouponInfoByUserIdForShop(CouponModel couponModel);
}