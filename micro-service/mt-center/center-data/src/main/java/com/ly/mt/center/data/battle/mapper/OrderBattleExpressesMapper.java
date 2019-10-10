package com.ly.mt.center.data.battle.mapper;

import com.ly.mt.center.data.battle.entity.OrderBattleExpresses;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderBattleExpressesMapper {
    /**
     * @Description 保存OrderBattleExpresses
     * @Author taoye
     */
    void insertOrderBattleExpresses(OrderBattleExpresses orderBattleExpresses);

    /**
     * @Description 删除OrderBattleExpresses
     * @Author taoye
     */
    void deleteOrderBattleExpresses(OrderBattleExpresses orderBattleExpresses);

    /**
     * @Description 更新OrderBattleExpresses
     * @Author taoye
     */
    int updateOrderBattleExpresses(OrderBattleExpresses orderBattleExpresses);

    /**
     * @Description 查询OrderBattleExpresses
     * @Author taoye
     */
    OrderBattleExpresses getOrderBattleExpresses(OrderBattleExpresses orderBattleExpresses);
}