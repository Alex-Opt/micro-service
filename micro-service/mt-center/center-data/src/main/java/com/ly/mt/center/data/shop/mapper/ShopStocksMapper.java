package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopStocks;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopStocksMapper {
    /**
     * @Description 保存ShopStocks
     * @Author taoye
     */
    void insertShopStocks(ShopStocks shopStocks);

    /**
     * @Description 删除ShopStocks
     * @Author taoye
     */
    void deleteShopStocks(ShopStocks shopStocks);

    /**
     * @Description 更新ShopStocks
     * @Author taoye
     */
    int updateShopStocks(ShopStocks shopStocks);

    /**
     * @Description 查询ShopStocks
     * @Author taoye
     */
    ShopStocks getShopStocks(ShopStocks shopStocks);
}