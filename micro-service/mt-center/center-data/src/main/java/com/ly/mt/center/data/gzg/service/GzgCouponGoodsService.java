package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgCouponGoodsService {
    /**
     * @Description 保存GzgCouponGoods
     * @Author taoye
     */
    ResponseJson insertGzgCouponGoods(JSONObject jsonObject);

    /**
     * @Description 删除GzgCouponGoods
     * @Author taoye
     */
    ResponseJson deleteGzgCouponGoods(JSONObject jsonObject);

    /**
     * @Description 更新GzgCouponGoods
     * @Author taoye
     */
    ResponseJson updateGzgCouponGoods(JSONObject jsonObject);

    /**
     * @Description 查询GzgCouponGoods
     * @Author taoye
     */
    ResponseJson getGzgCouponGoods(JSONObject jsonObject);
}