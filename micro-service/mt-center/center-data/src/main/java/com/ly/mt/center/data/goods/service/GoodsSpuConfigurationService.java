package com.ly.mt.center.data.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * Spu商品配置表接口层。
 * @date 2019-09-16
 * @author zhanglifeng
 */
public interface GoodsSpuConfigurationService {

    /**
     * 功能配置表中查询指定功能的商品spuId集合
     * @param jsonObject
     * @return
     */
    ResponseJson querySpuConfigGoods(JSONObject jsonObject);
}
