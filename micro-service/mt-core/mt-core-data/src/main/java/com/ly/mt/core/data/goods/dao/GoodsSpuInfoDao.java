package com.ly.mt.core.data.goods.dao;

import com.ly.mt.core.data.goods.entity.GoodsSpuInfo;

/**
 * GoodsSpuInfo操作接口
 *
 * @author taoye
 */
public interface GoodsSpuInfoDao {
    /**
     * 从reids根据id查询GoodsSpuInfo
     * redis不存在则查询mysql
     *
     * @param spuId spuId
     * @return GoodsSpuInfo
     * @author taoye
     */
    GoodsSpuInfo getGoodsSpuInfoByIdFromRedis(String spuId);
}