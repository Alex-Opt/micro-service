package com.ly.mt.purchase.server.shop.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 商家进货优惠
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-16 22:57:57
 */
public interface ShopPurchasesDiscountService {

    /**
     * B端 查询当前优惠信息
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getPurchasesDiscountInfoByShopId(String json) throws Exception;
}
