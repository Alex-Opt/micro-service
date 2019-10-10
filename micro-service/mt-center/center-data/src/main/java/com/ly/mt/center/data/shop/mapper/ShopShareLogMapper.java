package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopShareLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopShareLogMapper {
    /**
     * @Description 保存ShopShareLog
     * @Author taoye
     */
    void insertShopShareLog(ShopShareLog shopShareLog);

    /**
     * @Description 删除ShopShareLog
     * @Author taoye
     */
    void deleteShopShareLog(ShopShareLog shopShareLog);

    /**
     * @Description 更新ShopShareLog
     * @Author taoye
     */
    int updateShopShareLog(ShopShareLog shopShareLog);

    /**
     * @Description 查询ShopShareLog
     * @Author taoye
     */
    ShopShareLog getShopShareLog(ShopShareLog shopShareLog);
}