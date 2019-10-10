package com.ly.mt.center.data.goods.mapper;

import com.ly.mt.center.data.goods.entity.GoodsSkuPrice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsSkuPriceMapper {
    /**
     * @Description 保存GoodsSkuPrice
     * @Author taoye
     */
    void insertGoodsSkuPrice(GoodsSkuPrice goodsSkuPrice);

    /**
     * @Description 删除GoodsSkuPrice
     * @Author taoye
     */
    void deleteGoodsSkuPrice(GoodsSkuPrice goodsSkuPrice);

    /**
     * @Description 更新GoodsSkuPrice
     * @Author taoye
     */
    int updateGoodsSkuPrice(GoodsSkuPrice goodsSkuPrice);

    /**
     * @Description 查询GoodsSkuPrice
     * @Author taoye
     */
    GoodsSkuPrice getGoodsSkuPrice(GoodsSkuPrice goodsSkuPrice);
}