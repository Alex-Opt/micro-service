package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopInfoMapper{
    /**
     * @Description 插入ShopInfo
     * @Author taoye
     */
    void insertShopInfo(ShopInfo shopInfo);

    /**
     * @Description 根据id删除ShopInfo
     * @Author taoye
     */
    void deleteShopInfoById(ShopInfo shopInfo);

    /**
     * @Description 根据id更新ShopInfo
     * @Author taoye
     */
    int updateShopInfoById(ShopInfo shopInfo);


    /**
     * @Description 根据id查询ShopInfo
     * @Author taoye
     */
    ShopInfo getShopInfo(ShopInfo record);
}