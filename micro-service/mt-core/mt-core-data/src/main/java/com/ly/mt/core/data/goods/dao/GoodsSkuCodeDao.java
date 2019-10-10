package com.ly.mt.core.data.goods.dao;

import com.ly.mt.core.data.goods.entity.GoodsSkuCode;

import java.util.List;

/**
 * GoodsSkuCode操作接口
 *
 * @author taoye
 */
public interface GoodsSkuCodeDao {
    /**
     * 新增GoodsSkuCode
     *
     * @param goodsSkuCodes 新增数据
     * @author taoye
     */
    void insertGoodsSkuCodes(List<GoodsSkuCode> goodsSkuCodes);

    /**
     * 从reids根据skuId查询GoodsSkuCode
     * redis不存在则查询mysql
     *
     * @param skuId skuId
     * @return GoodsSkuCode
     * @author taoye
     */
    GoodsSkuCode getGoodsSkuCodeBySkuIdFromRedis(String skuId);
}