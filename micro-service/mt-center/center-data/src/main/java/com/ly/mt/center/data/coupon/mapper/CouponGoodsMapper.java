package com.ly.mt.center.data.coupon.mapper;

import com.ly.mt.center.data.coupon.entity.CouponGoods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponGoodsMapper {
    /**
     * @Description 保存CouponGoods
     * @Author taoye
     */
    void insertCouponGoods(CouponGoods couponGoods);

    /**
     * @Description 删除CouponGoods
     * @Author taoye
     */
    void deleteCouponGoods(CouponGoods couponGoods);

    /**
     * @Description 更新CouponGoods
     * @Author taoye
     */
    int updateCouponGoods(CouponGoods couponGoods);

    /**
     * @Description 查询CouponGoods
     * @Author taoye
     */
    CouponGoods getCouponGoods(CouponGoods couponGoods);
}