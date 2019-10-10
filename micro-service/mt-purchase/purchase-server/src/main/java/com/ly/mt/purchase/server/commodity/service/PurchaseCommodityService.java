package com.ly.mt.purchase.server.commodity.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 获取商品接口
 */
public interface PurchaseCommodityService {

    /**
     * 获取商品信息
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getCommodityInfo(String json) throws Exception;

    /**
     * 获取商品详情
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getCommodityDetail(String json) throws Exception;

    /**
     * 通过spuId获取商品
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getCommodityBySpuId(String json) throws Exception;

    /**
     * 获取商品阶梯价
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getCommodityLadderPrice(String json) throws Exception;

    /**
     * 获取店铺优惠劵信息
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getShopCouponInfo(String json) throws Exception;
}
