package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopDefault;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopDefaultMapper {
    /**
     * @Description 保存ShopDefault
     * @Author taoye
     */
    void insertShopDefault(ShopDefault shopDefault);

    /**
     * @Description 删除ShopDefault
     * @Author taoye
     */
    void deleteShopDefault(ShopDefault shopDefault);

    /**
     * @Description 更新ShopDefault
     * @Author taoye
     */
    int updateShopDefault(ShopDefault shopDefault);

    /**
     * @Description 查询ShopDefault
     * @Author taoye
     */
    ShopDefault getShopDefault(ShopDefault shopDefault);
}