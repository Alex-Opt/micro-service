package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 格子柜六号格子的商品套装对应的商品明细操作
 *
 * @author zhanglifeng
 * @date 2019-08-27
 */
public interface GzgSuitBarcodeInfoService {
    /**
     * 根据套装sku_id查询套装下的商品信息
     *
     * @param jsonObject
     * @return
     */
    ResponseJson queryBySuitSpuId(JSONObject jsonObject);
}
