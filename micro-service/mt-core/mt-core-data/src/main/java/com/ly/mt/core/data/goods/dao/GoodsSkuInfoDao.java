package com.ly.mt.core.data.goods.dao;

import com.ly.mt.core.data.goods.entity.GoodsSkuInfo;

/**
 * GoodsSkuInfo操作接口
 *
 * @author taoye
 */
public interface GoodsSkuInfoDao {
    /**
     * 从reids根据id查询GoodsSkuInfo
     * redis不存在则查询mysql
     *
     * @param id id
     * @return GoodsSkuInfo
     * @author taoye
     */
    GoodsSkuInfo getGoodsSkuInfoByIdFromRedis(String id);
}