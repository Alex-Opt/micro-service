package com.ly.mt.center.data.battle.mapper;

import com.ly.mt.center.data.battle.entity.OrderBattleCheckLogs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderBattleCheckLogsMapper {
    /**
     * @Description 保存OrderBattleCheckLogs
     * @Author taoye
     */
    void insertOrderBattleCheckLogs(OrderBattleCheckLogs orderBattleCheckLogs);

    /**
     * @Description 删除OrderBattleCheckLogs
     * @Author taoye
     */
    void deleteOrderBattleCheckLogs(OrderBattleCheckLogs orderBattleCheckLogs);

    /**
     * @Description 更新OrderBattleCheckLogs
     * @Author taoye
     */
    int updateOrderBattleCheckLogs(OrderBattleCheckLogs orderBattleCheckLogs);

    /**
     * @Description 查询OrderBattleCheckLogs
     * @Author taoye
     */
    OrderBattleCheckLogs getOrderBattleCheckLogs(OrderBattleCheckLogs orderBattleCheckLogs);
}