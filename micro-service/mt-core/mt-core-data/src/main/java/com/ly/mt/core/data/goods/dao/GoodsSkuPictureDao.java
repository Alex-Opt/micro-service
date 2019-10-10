package com.ly.mt.core.data.goods.dao;

import com.ly.mt.core.data.goods.entity.GoodsSkuPicture;

/**
 * GoodsSkuPicture操作接口
 *
 * @author taoye
 */
public interface GoodsSkuPictureDao {
    /**
     * 从reids根据skuId查询GoodsSkuPicture
     * redis不存在则查询mysql
     *
     * @param skuId skuId
     * @return 图片地址
     * @author taoye
     */
    GoodsSkuPicture getGoodsSkuPictureBySkuIdFromRedis(String skuId);
}