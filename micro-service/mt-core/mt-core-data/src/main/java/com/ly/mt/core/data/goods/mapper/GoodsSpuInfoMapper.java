package com.ly.mt.core.data.goods.mapper;

import com.ly.mt.core.data.goods.entity.GoodsSpuInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * GoodsSpuInfo操作接口
 *
 * @author taoye
 */
@Mapper
public interface GoodsSpuInfoMapper {
    /**
     * 查询GoodsSpuInfo
     *
     * @author taoye
     */
    GoodsSpuInfo getGoodsSpuInfo(GoodsSpuInfo goodsSpuInfo);
}