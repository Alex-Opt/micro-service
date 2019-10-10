package com.ly.mt.center.data.coupon.mapper;

import com.ly.mt.center.data.coupon.entity.CouponCode;
import com.ly.mt.center.data.coupon.entity.CouponModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CouponCodeMapper {
    /**
     * @Description 保存CouponCode
     * @Author taoye
     */
    void insertCouponCode(CouponCode couponCode);

    /**
     * @Description 删除CouponCode
     * @Author taoye
     */
    void deleteCouponCode(CouponCode couponCode);

    /**
     * @Description 更新CouponCode
     * @Author taoye
     */
    int updateCouponCode(CouponCode couponCode);

    /**
     * @Description 查询CouponCode
     * @Author taoye
     */
    CouponCode getCouponCode(CouponCode couponCode);

    /**
     * 用户兑换 兑换码
     *
     * @param couponCode
     * @return
     */
    int updateConponCodeByCode(CouponCode couponCode);

    /**
     * 根据用户id,优惠券id查询用户是否领取该优惠券
     *
     * @param userId
     * @param list
     * @return
     */
    List<CouponCode> queryCouponListByUserIdAndCouponIds(@Param("userId") Long userId, @Param("list") List<Long> list);


    /**
     * 根据用户id,优惠券id统计用户是否领取该优惠券
     *
     * @param userId
     * @param list
     * @return
     */
    List<Map<String, String>> countCouponCodeByUserIdAndCouponIds(@Param("userId") Long userId, @Param("list") List<Long> list);

    /**
     * 根据用户id,couponCode查询是否已经兑换优惠券
     * @param userId
     * @param couponCode
     * @return
     */
    CouponCode getCouponCodeInfoByUserIdAndCouponCode(@Param("userId") Long userId, @Param("couponCode") String couponCode);

    /**
     * 根据兑换码查询出一条数据
     * @param code
     * @return
     */
    CouponCode getOneCouponCodeByCode(String code);

    /**
     * 到家b查询couponCode
     * @param couponCode
     * @return
     */
    CouponModel getCouponCodeForShop(CouponCode couponCode);
}