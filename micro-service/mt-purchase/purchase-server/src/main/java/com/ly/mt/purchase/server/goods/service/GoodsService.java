package com.ly.mt.purchase.server.goods.service;


import com.alibaba.fastjson.JSONObject;

/**
 * 商品相关
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-17 23:33:33
 */
public interface GoodsService {


    /**
     * B端 类目查询
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject queryCategroyListByParentId(String json) throws Exception;

    /**
     * B端 销量top5
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject queryListTop5ByCid(String json) throws Exception;
}
