package com.ly.mt.core.data.shop.dao;

import com.ly.mt.core.data.shop.entity.ShopInfo;

import java.util.List;

/**
 * ShopInfo操作接口
 *
 * @author taoye
 */
public interface ShopInfoDao {
    /**
     * 从reids根据id查询ShopInfo
     * redis不存在则查询mysql
     *
     * @param id 店铺ID
     * @return ShopInfo
     * @author taoye
     */
    ShopInfo getShopInfoByIdFromRedis(String id);

    /**
     * 从mysql查询List<ShopInfo>
     *
     * @param shopInfo 查询条件
     * @return List<ShopInfo>
     * @author taoye
     */
    List<ShopInfo> listShopInfoFromMysql(ShopInfo shopInfo);
}