package com.ly.mt.core.data.order.mapper;

import com.ly.mt.core.data.order.entity.OrderBattleShop;
import com.ly.mt.core.data.order.entity.OrdersBattleInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * OrdersBattleInfo操作接口
 *
 * @author taoye
 */
@Mapper
public interface OrderBattleShopMapper {
    /**
     * 更新OrdersBattleShop
     *
     * @param orderBattleShop 更新条件和数据
     * @author taoye
     */
    int updateOrderBattleShop(OrderBattleShop orderBattleShop);
}