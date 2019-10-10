package com.ly.mt.core.data.goods.mapper;

import com.ly.mt.core.data.goods.entity.GoodsSkuInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * GoodsSkuInfo操作接口
 *
 * @author taoye
 */
@Mapper
public interface GoodsSkuInfoMapper {
    /**
     * 查询GoodsSkuInfo
     *
     * @author taoye
     */
    GoodsSkuInfo getGoodsSkuInfo(GoodsSkuInfo goodsSkuInfo);
}