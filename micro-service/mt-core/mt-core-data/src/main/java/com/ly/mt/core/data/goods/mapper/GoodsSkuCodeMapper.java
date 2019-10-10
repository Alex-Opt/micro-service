package com.ly.mt.core.data.goods.mapper;

import com.ly.mt.core.data.goods.entity.GoodsSkuCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * GoodsSkuCode操作接口
 *
 * @author taoye
 */
@Mapper
public interface GoodsSkuCodeMapper {
    /**
     * 新增GoodsSkuCode
     *
     * @param goodsSkuCodes 新增数据
     * @author taoye
     */
    void insertGoodsSkuCodes(List<GoodsSkuCode> goodsSkuCodes);

    /**
     * 根据skuId查询GoodsSkuInfo
     *
     * @author taoye
     */
    GoodsSkuCode getGoodsSkuCode(GoodsSkuCode goodsSkuCode);
}