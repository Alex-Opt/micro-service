package com.ly.mt.purchase.server.cart.service;

import com.alibaba.fastjson.JSONObject;

public interface CartService {

    /**
     * 加入购物车
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject addCart(String json) throws Exception;

    /**
     * 购物车减少商品数量
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject subCartSkuNum(String json) throws Exception;

    /**
     * 删除购物车商品
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject delCartSku(String json) throws Exception;


    /**
     * 获取用户购物车信息
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getPurchaseCart(String json) throws Exception;

    /**
     * 同步购物车
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject syncCart(String json) throws Exception;
}
