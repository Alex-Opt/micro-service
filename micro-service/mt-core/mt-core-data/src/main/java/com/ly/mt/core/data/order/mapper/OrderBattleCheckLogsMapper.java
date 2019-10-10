package com.ly.mt.core.data.order.mapper;

import com.ly.mt.core.data.order.entity.OrderBattleCheckLogs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * OrderBattleCheckLogs操作接口
 *
 * @author taoye
 */
@Mapper
public interface OrderBattleCheckLogsMapper {
    /**
     * 查询List<OrderBattleCheckLogs>
     *
     * @param orderBattleCheckLogs 查询条件
     * @return List<OrderBattleCheckLogs>
     * @author taoye
     */
    List<OrderBattleCheckLogs> listOrderBattleCheckLogs(OrderBattleCheckLogs orderBattleCheckLogs);
}