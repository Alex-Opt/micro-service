package com.ly.mt.order.server.coupon.mapper;


import com.ly.mt.core.base.entity.coupon.CouponGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券-商品持久层
 *
 * @author zhanglifeng
 * @date 2019-05-25
 */
@Mapper
public interface CouponGoodsServiceMapper {


    CouponGoods selectByPrimaryKey(Long id);

    /**
     * 根据限定商品的优惠券id集合，购买商品spuId集合查询coupon_goods表是否有满足条件的优惠券存在。
     *
     * @param spuIdList
     * @param couponId
     * @return
     */
    List<CouponGoods> selectByCouponIdAndSpuIds(@Param("spuIdList") List spuIdList, @Param("couponId") String couponId);

}