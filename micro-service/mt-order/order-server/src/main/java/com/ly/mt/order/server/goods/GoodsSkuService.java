package com.ly.mt.order.server.goods;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.goods.GoodsSkuInfo;
import com.ly.mt.core.base.entity.goods.GoodsSpuInfoForSkuVo;

/**
 * 商品sku业务层
 */
public interface GoodsSkuService {

    /**
     * 查询商品sku销售属性数据
     *
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject queryGoodsSku(String json) throws Exception;

    /**
     * 根据商品的skuId查询出商品的基本信息
     * @param id
     * @return
     */
    GoodsSkuInfo getGoodsSkuInfoBySkuId(Long id);

    /**
     * 根据主键获取spuinfo信息
     * @param spuId
     * @return
     */
    GoodsSpuInfoForSkuVo getGoodsSpuInfoBySpuId(Long spuId);

    /**
     * 根据spuId查询sku信息
     * @param json
     * @return
     */
    JSONObject getSkuInfoBySpuIdForOk(String json);
}
