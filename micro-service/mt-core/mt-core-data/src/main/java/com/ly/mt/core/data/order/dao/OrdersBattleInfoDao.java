package com.ly.mt.core.data.order.dao;

import com.ly.mt.core.data.order.entity.OrdersBattleInfo;

import java.util.List;

/**
 * OrdersBattleInfo操作接口
 *
 * @author taoye
 */
public interface OrdersBattleInfoDao {
    /**
     * 从redis根据oriderId查询OrdersBattleInfo
     * redis不存在则查询mysql
     *
     * @param orderId 订单ID
     * @return OrdersBattleInfo
     * @author taoye
     */
    OrdersBattleInfo getOrdersBattleInfoByOrderIdFromRedis(String orderId);

    /**
     * 从mysql查询List<OrdersBattleInfo>
     *
     * @param ordersBattleInfo 查询条件
     * @return List<OrdersBattleInfo>
     * @author taoye
     */
    List<OrdersBattleInfo> listOrdersBattleInfoFromMysql(OrdersBattleInfo ordersBattleInfo);

    /**
     * 更新OrdersBattleInfo
     *
     * @param ordersBattleInfo 更新条件和数据
     * @return 更新结果
     * @author taoye
     */
    int updateOrdersBattleInfo(OrdersBattleInfo ordersBattleInfo);
}