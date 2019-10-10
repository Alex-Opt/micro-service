package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopRefundImages;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopRefundImagesMapper {
    /**
     * @Description 保存ShopRefundImages
     * @Author taoye
     */
    void insertShopRefundImages(ShopRefundImages shopRefundImages);

    /**
     * @Description 删除ShopRefundImages
     * @Author taoye
     */
    void deleteShopRefundImages(ShopRefundImages shopRefundImages);

    /**
     * @Description 更新ShopRefundImages
     * @Author taoye
     */
    int updateShopRefundImages(ShopRefundImages shopRefundImages);

    /**
     * @Description 查询ShopRefundImages
     * @Author taoye
     */
    ShopRefundImages getShopRefundImages(ShopRefundImages shopRefundImages);
}