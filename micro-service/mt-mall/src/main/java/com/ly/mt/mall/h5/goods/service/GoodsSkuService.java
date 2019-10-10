package com.ly.mt.mall.h5.goods.service;

import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 商品sku业务层
 */
public interface GoodsSkuService {

    /**
     * 查询商品sku销售属性数据
     *
     * @param
     * @return
     * @throws Exception
     */
    ResponseJson getGoodsSkuInfoBySpuIdAndAttr(String spuId, String attributes) throws Exception;


    /**
     * 根据spuId查询sku信息
     *
     * @param spuId
     * @return
     */
    ResponseJson getSkuInfoBySpuIdForOk(String spuId) throws Exception;
}
