package com.ly.mt.center.data.battle.mapper;

import com.ly.mt.center.data.battle.entity.OrderBattleLogs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderBattleLogsMapper {
    /**
     * @Description 保存OrderBattleLogs
     * @Author taoye
     */
    void insertOrderBattleLogs(OrderBattleLogs orderBattleLogs);

    /**
     * @Description 删除OrderBattleLogs
     * @Author taoye
     */
    void deleteOrderBattleLogs(OrderBattleLogs orderBattleLogs);

    /**
     * @Description 更新OrderBattleLogs
     * @Author taoye
     */
    int updateOrderBattleLogs(OrderBattleLogs orderBattleLogs);

    /**
     * @Description 查询OrderBattleLogs
     * @Author taoye
     */
    OrderBattleLogs getOrderBattleLogs(OrderBattleLogs orderBattleLogs);
}