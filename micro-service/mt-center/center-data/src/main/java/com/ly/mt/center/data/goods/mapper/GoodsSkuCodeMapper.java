package com.ly.mt.center.data.goods.mapper;

import com.ly.mt.center.data.goods.entity.GoodsSkuCode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsSkuCodeMapper {
    /**
     * @Description 保存GoodsSkuCode
     * @Author taoye
     */
    void insertGoodsSkuCode(GoodsSkuCode goodsSkuCode);

    /**
     * @Description 删除GoodsSkuCode
     * @Author taoye
     */
    void deleteGoodsSkuCode(GoodsSkuCode goodsSkuCode);

    /**
     * @Description 更新GoodsSkuCode
     * @Author taoye
     */
    int updateGoodsSkuCode(GoodsSkuCode goodsSkuCode);

    /**
     * @Description 查询GoodsSkuCode
     * @Author taoye
     */
    GoodsSkuCode getGoodsSkuCode(GoodsSkuCode goodsSkuCode);
}