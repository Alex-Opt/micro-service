package com.ly.mt.purchase.server.shop.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 店铺会员等级配置
 * @author xiaobei
 * @date 2019-06-21 08:12:12
 */
public interface ShopGradeService {


    /**
     * 获取所有的店铺会员等级配置
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject listAll(String json) throws Exception;
}
