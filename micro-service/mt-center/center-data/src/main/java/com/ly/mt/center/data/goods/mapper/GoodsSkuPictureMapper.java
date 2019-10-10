package com.ly.mt.center.data.goods.mapper;

import com.ly.mt.center.data.goods.entity.GoodsSkuPicture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsSkuPictureMapper {
    /**
     * @Description 插入GoodsSkuPicture
     * @Author taoye
     */
    void insertGoodsSkuPicture(GoodsSkuPicture goodsSkuPicture);

    /**
     * @Description 根据id删除GoodsSkuPicture
     * @Author taoye
     */
    void deleteGoodsSkuPictureById(GoodsSkuPicture goodsSkuPicture);

    /**
     * @Description 根据id更新GoodsSkuPicture
     * @Author taoye
     */
    int updateGoodsSkuPictureById(GoodsSkuPicture goodsSkuPicture);

    /**
     * @Description 根据条件查询GoodsSkuPicture
     * @Author taoye
     */
    GoodsSkuPicture getGoodsSkuPicture(GoodsSkuPicture goodsSkuPicture);

    /**
     * @Description 根据id查询GoodsSkuPicture
     * @Author taoye
     */
    GoodsSkuPicture getGoodsSkuPictureById(GoodsSkuPicture goodsSkuPicture);

    /**
     * @Description 根据skuId查询GoodsSkuPicture
     * @Author
     */
    List<GoodsSkuPicture> getGoodsSkuPictureBySkuId(GoodsSkuPicture goodsSkuPicture);

    /**
     * @Description 根据skuId查询GoodsSkuPicture
     * @Author
     */
    GoodsSkuPicture getSkuPictureBySkuId(GoodsSkuPicture goodsSkuPicture);
}