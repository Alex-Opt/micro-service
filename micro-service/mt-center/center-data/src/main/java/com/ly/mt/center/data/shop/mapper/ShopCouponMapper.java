package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopCoupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopCouponMapper {
    /**
     * @Description 保存ShopCoupon
     * @Author taoye
     */
    void insertShopCoupon(ShopCoupon shopCoupon);

    /**
     * @Description 删除ShopCoupon
     * @Author taoye
     */
    void deleteShopCoupon(ShopCoupon shopCoupon);

    /**
     * @Description 更新ShopCoupon
     * @Author taoye
     */
    int updateShopCoupon(ShopCoupon shopCoupon);

    /**
     * @Description 查询ShopCoupon
     * @Author taoye
     */
    ShopCoupon getShopCoupon(ShopCoupon shopCoupon);

    /**
     * @Description 查询ShopCouponList
     * @Author taoye
     */
    List<ShopCoupon> getShopCouponList(ShopCoupon shopCoupon);
}