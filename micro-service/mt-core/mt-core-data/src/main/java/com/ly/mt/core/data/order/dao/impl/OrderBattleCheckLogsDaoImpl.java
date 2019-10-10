package com.ly.mt.core.data.order.dao.impl;

import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.order.dao.OrderBattleCheckLogsDao;
import com.ly.mt.core.data.order.entity.OrderBattleCheckLogs;
import com.ly.mt.core.data.order.mapper.OrderBattleCheckLogsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * OrderBattleCheckLogs操作接口
 *
 * @author taoye
 */
@Service
public class OrderBattleCheckLogsDaoImpl extends BaseDaoServiceImpl implements OrderBattleCheckLogsDao {
    @Resource
    private OrderBattleCheckLogsMapper mapper;

    @Override
    public List<OrderBattleCheckLogs> listOrderBattleCheckLogsFromMysql(OrderBattleCheckLogs orderBattleCheckLogs) {
        return mapper.listOrderBattleCheckLogs(orderBattleCheckLogs);
    }
}