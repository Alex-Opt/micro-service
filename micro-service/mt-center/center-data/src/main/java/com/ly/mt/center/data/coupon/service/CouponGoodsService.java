package com.ly.mt.center.data.coupon.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface CouponGoodsService {
    /**
     * @Description 保存CouponGoods
     * @Author taoye
     */
    ResponseJson insertCouponGoods(JSONObject jsonObject);

    /**
     * @Description 删除CouponGoods
     * @Author taoye
     */
    ResponseJson deleteCouponGoods(JSONObject jsonObject);

    /**
     * @Description 更新CouponGoods
     * @Author taoye
     */
    ResponseJson updateCouponGoods(JSONObject jsonObject);

    /**
     * @Description 查询CouponGoods
     * @Author taoye
     */
    ResponseJson getCouponGoods(JSONObject jsonObject);
}