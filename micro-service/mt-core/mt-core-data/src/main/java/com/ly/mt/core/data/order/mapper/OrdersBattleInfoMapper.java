package com.ly.mt.core.data.order.mapper;

import com.ly.mt.core.data.order.entity.OrdersBattleInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * OrdersBattleInfo操作接口
 *
 * @author taoye
 */
@Mapper
public interface OrdersBattleInfoMapper {
    /**
     * 查询OrderBattleInfo
     *
     * @param ordersBattleInfo 查询条件
     * @return OrdersBattleInfo
     * @author taoye
     */
    OrdersBattleInfo getOrdersBattleInfo(OrdersBattleInfo ordersBattleInfo);

    /**
     * 查询List<OrdersBattleInfo>
     *
     * @param ordersBattleInfo 查询条件
     * @return List<OrdersBattleInfo>
     * @author taoye
     */
    List<OrdersBattleInfo> listOrdersBattleInfo(OrdersBattleInfo ordersBattleInfo);

    /**
     * 更新OrdersBattleInfo
     *
     * @param ordersBattleInfo 更新条件和数据
     * @return 更新结果
     * @author taoye
     */
    int updateOrdersBattleInfo(OrdersBattleInfo ordersBattleInfo);
}