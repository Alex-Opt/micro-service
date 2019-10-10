package com.ly.mt.home.tob.goods.service;


import com.ly.mt.home.tob.goods.vo.GoodsSkuVo;

/**
 * 商品sku业务层
 *
 * @author: linan
 * @date: 2019/9/19
 **/
public interface GoodsSkuService {

    /**
     * 查询商品sku销售属性数据
     *
     * @param spuId
     * @param attributes
     * @return
     */
    GoodsSkuVo getGoodsSkuInfoBySpuIdAndAttr(String spuId, String attributes);

    /**
     * 根据id查询sku
     *
     * @param id
     * @return
     */
    GoodsSkuVo getGoodsSkuInfoById(String id);
}
