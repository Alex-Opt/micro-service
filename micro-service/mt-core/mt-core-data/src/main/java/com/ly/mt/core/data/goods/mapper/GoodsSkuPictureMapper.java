package com.ly.mt.core.data.goods.mapper;

import com.ly.mt.core.data.goods.entity.GoodsSkuPicture;
import org.apache.ibatis.annotations.Mapper;

/**
 * GoodsSkuPicture操作接口
 *
 * @author taoye
 */
@Mapper
public interface GoodsSkuPictureMapper {
    /**
     * 查询GoodsSkuPicture
     *
     * @author taoye
     */
    GoodsSkuPicture getGoodsSkuPicture(GoodsSkuPicture goodsSkuPicture);
}