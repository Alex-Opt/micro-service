package com.ly.mt.order.server.coupon.mapper;

import com.ly.mt.core.base.entity.coupon.CouponCode;
import com.ly.mt.core.base.entity.coupon.CouponInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 优惠券信息
 */
@Mapper
public interface CouponInfoServiceMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CouponInfo record);

    int insertSelective(CouponInfo record);

    CouponInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CouponInfo record);

    int updateByPrimaryKey(CouponInfo record);


    /**
     * 根据优惠券id和用户id查询用户已领取数据
     *
     * @param couponId
     * @param userId
     * @return
     */
    CouponCode queryCouponCodeByCouponIdAndUserId(@Param("couponId") String couponId, @Param("userId") String userId);


    /**
     * 保存用户领取优惠券数据
     *
     * @param entity
     * @return
     */
    int saveCouponCode(CouponCode entity);

    /**
     * 根据conponid的集合查询出到家C符合条件的优惠券集合
     *
     * @param list
     * @return
     */
    List<CouponInfo> batchQueryByCouponId(@Param("list") List<String> list, @Param("nowTime") String nowTime);

    /**
     * 根据conpon_id的集合去表coupon_code中查询出在有效期范围呢的优惠券集合
     *
     * @param list
     * @param nowTime
     * @param userId
     * @return
     */
    List<CouponCode> queryCouponCodeListByCouponIdList(@Param("list") List<String> list, @Param("nowTime") String nowTime, @Param("userId") Long userId);

    /**
     * 批量生成优惠券兑换码
     *
     * @param list
     * @return
     */
    int batchInsertCouponCode(@Param("list") List<CouponCode> list);

    /**
     * 把兑换码对应的优惠券的用户信息更新进去
     *
     * @param userId
     * @param pullTime
     * @param couponCode
     * @return
     */
    int updateConponUserInfoByCouponCode(@Param("userId") String userId, @Param("pullTime") String pullTime, @Param("couponCode") String couponCode);

    /**
     * 查询用户优惠券信息
     *
     * @param userId
     * @return
     * @Param nowTime
     */
    List<CouponInfo> getCouponInfoByUserId(@Param("userId") String userId, @Param("nowTime") String nowTime);

    /**
     * 获取当前有效的通用优惠券id
     *
     * @return
     */
    List<String> getCouponIdsByLimitType(@Param("nowDate") String nowDate);
}
