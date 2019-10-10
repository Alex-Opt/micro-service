package com.ly.mt.core.data.order.dao;

import com.ly.mt.core.data.order.entity.OrderBattleShop;

/**
 * OrderBattleShop操作接口
 *
 * @author taoye
 */
public interface OrderBattleShopDao {
    /**
     * 更新OrderBattleShop
     *
     * @param orderBattleShop 更新条件和数据
     * @author taoye
     */
    int updateOrderBattleShop(OrderBattleShop orderBattleShop);
}