package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopCouponService {
    /**
     * @Description 保存ShopCoupon
     * @Author taoye
     */
    ResponseJson insertShopCoupon(JSONObject jsonObject);

    /**
     * @Description 删除ShopCoupon
     * @Author taoye
     */
    ResponseJson deleteShopCoupon(JSONObject jsonObject);

    /**
     * @Description 更新ShopCoupon
     * @Author taoye
     */
    ResponseJson updateShopCoupon(JSONObject jsonObject);

    /**
     * @Description 查询ShopCoupon
     * @Author taoye
     */
    ResponseJson getShopCoupon(JSONObject jsonObject);

    /**
     * @Description 查询ShopCouponList
     * @Author taoye
     */
    ResponseJson getShopCouponList(JSONObject jsonObject);
}