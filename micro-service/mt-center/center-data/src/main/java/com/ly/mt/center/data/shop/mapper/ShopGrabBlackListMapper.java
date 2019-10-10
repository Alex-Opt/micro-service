package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopGrabBlackList;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopGrabBlackListMapper {
    /**
     * @Description 保存ShopGrabBlackList
     * @Author taoye
     */
    void insertShopGrabBlackList(ShopGrabBlackList shopGrabBlackList);

    /**
     * @Description 删除ShopGrabBlackList
     * @Author taoye
     */
    void deleteShopGrabBlackList(ShopGrabBlackList shopGrabBlackList);

    /**
     * @Description 更新ShopGrabBlackList
     * @Author taoye
     */
    int updateShopGrabBlackList(ShopGrabBlackList shopGrabBlackList);

    /**
     * @Description 查询ShopGrabBlackList
     * @Author taoye
     */
    ShopGrabBlackList getShopGrabBlackList(ShopGrabBlackList shopGrabBlackList);
}