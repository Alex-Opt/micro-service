package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopProfitLogs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopProfitLogsMapper {
    /**
     * @Description 保存ShopProfitLogs
     * @Author taoye
     */
    void insertShopProfitLogs(ShopProfitLogs shopProfitLogs);

    /**
     * @Description 删除ShopProfitLogs
     * @Author taoye
     */
    void deleteShopProfitLogs(ShopProfitLogs shopProfitLogs);

    /**
     * @Description 更新ShopProfitLogs
     * @Author taoye
     */
    int updateShopProfitLogs(ShopProfitLogs shopProfitLogs);

    /**
     * @Description 查询ShopProfitLogs
     * @Author taoye
     */
    ShopProfitLogs getShopProfitLogs(ShopProfitLogs shopProfitLogs);
}