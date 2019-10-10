package com.ly.mt.purchase.server.shop.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 购买情况
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-17 22:08:08
 */
public interface PurchaseService {

    /**
     * B端 获取最新的购买情况
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getNewestPurchasesInfo(String json) throws Exception;
}
