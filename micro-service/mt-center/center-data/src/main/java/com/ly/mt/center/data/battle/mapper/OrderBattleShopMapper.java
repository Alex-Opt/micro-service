package com.ly.mt.center.data.battle.mapper;

import com.ly.mt.center.data.battle.entity.OrderBattleShop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderBattleShopMapper {
    /**
     * @Description 保存OrderBattleShop
     * @Author taoye
     */
    void insertOrderBattleShop(OrderBattleShop orderBattleShop);

    /**
     * @Description 删除OrderBattleShop
     * @Author taoye
     */
    void deleteOrderBattleShop(OrderBattleShop orderBattleShop);

    /**
     * @Description 更新OrderBattleShop
     * @Author taoye
     */
    int updateOrderBattleShop(OrderBattleShop orderBattleShop);

    /**
     * @Description 查询OrderBattleShop
     * @Author taoye
     */
    OrderBattleShop getOrderBattleShop(OrderBattleShop orderBattleShop);
}