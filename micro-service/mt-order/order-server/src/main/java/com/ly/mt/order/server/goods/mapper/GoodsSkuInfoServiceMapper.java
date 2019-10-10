package com.ly.mt.order.server.goods.mapper;


import com.ly.mt.core.base.entity.goods.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsSkuInfoServiceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsSkuInfo record);

    int insertSelective(GoodsSkuInfo record);

    GoodsSkuInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSkuInfo record);

    int updateByPrimaryKey(GoodsSkuInfo record);

    List<GoodsSkuInfo> selectBySpuId(@Param("spuId") Long spuId);

    /**
     * 根据商品spuId查询商品销售属性
     *@param spuId
     * @param attributes
     * @return
     */
    GoodsSkuInfoVo queryGoodsSku(@Param("spuId") Long spuId, @Param("attributes") String attributes);

    /**
     * 查询商品sku价格数据
     * @param skuId
     * @return
     */
    GoodsSkuPrice queryGoodsSkuPriceBySkuId(Long skuId);

    /**
     * 查询商品sku图片数据
     * @param skuId
     * @return
     */
    List<GoodsSkuPicture> queryGoodsSkuPictureBySkuId(Long skuId);

    /**
     * 根据spuId查询spu基本信息
     * @param spuId
     * @return
     */
    GoodsSpuInfoForSkuVo getGoodsSpuInfoBySpuId(Long spuId);
}