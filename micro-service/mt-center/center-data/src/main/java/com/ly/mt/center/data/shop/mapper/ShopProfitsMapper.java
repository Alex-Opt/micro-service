package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopProfits;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopProfitsMapper {
    /**
     * @Description 保存ShopProfits
     * @Author taoye
     */
    void insertShopProfits(ShopProfits shopProfits);

    /**
     * @Description 删除ShopProfits
     * @Author taoye
     */
    void deleteShopProfits(ShopProfits shopProfits);

    /**
     * @Description 更新ShopProfits
     * @Author taoye
     */
    int updateShopProfits(ShopProfits shopProfits);

    /**
     * @Description 查询ShopProfits
     * @Author taoye
     */
    ShopProfits getShopProfits(ShopProfits shopProfits);
}