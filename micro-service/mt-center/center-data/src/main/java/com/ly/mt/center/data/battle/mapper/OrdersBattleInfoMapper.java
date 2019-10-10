package com.ly.mt.center.data.battle.mapper;

import com.ly.mt.center.data.battle.entity.OrdersBattleInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersBattleInfoMapper {
    /**
     * @Description 保存OrdersBattleInfo
     * @Author taoye
     */
    void insertOrdersBattleInfo(OrdersBattleInfo ordersBattleInfo);

    /**
     * @Description 删除OrdersBattleInfo
     * @Author taoye
     */
    void deleteOrdersBattleInfo(OrdersBattleInfo ordersBattleInfo);

    /**
     * @Description 更新OrdersBattleInfo
     * @Author taoye
     */
    int updateOrdersBattleInfo(OrdersBattleInfo ordersBattleInfo);

    /**
     * @Description 查询OrdersBattleInfo
     * @Author taoye
     */
    OrdersBattleInfo getOrdersBattleInfo(OrdersBattleInfo ordersBattleInfo);
}