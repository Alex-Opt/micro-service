package com.ly.mt.core.data.order.dao;

import com.ly.mt.core.data.order.entity.OrderBattleCheckLogs;

import java.util.List;

/**
 * OrderBattleCheckLogs操作接口
 *
 * @author taoye
 */
public interface OrderBattleCheckLogsDao {
    /**
     * 查询List<OrderBattleCheckLogs>
     *
     * @param orderBattleCheckLogs 查询条件
     * @return List<OrderBattleCheckLogs>
     * @author taoye
     */
    List<OrderBattleCheckLogs> listOrderBattleCheckLogsFromMysql(OrderBattleCheckLogs orderBattleCheckLogs);
}