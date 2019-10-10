package com.ly.mt.core.data.order.dao.impl;

import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.order.dao.OrderBattleShopDao;
import com.ly.mt.core.data.order.entity.OrderBattleShop;
import com.ly.mt.core.data.order.mapper.OrderBattleShopMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * OrderBattleShop操作接口
 *
 * @author taoye
 */
@Service
public class OrderBattleShopDaoImpl extends BaseDaoServiceImpl implements OrderBattleShopDao {
    @Resource
    private OrderBattleShopMapper mapper;

    @Override
    public int updateOrderBattleShop(OrderBattleShop orderBattleShop) {
        Assert.notNull(orderBattleShop, "OrderBattleShopDaoImpl.updateOrderBattleShop orderBattleShop must not be null");
        return mapper.updateOrderBattleShop(orderBattleShop);
    }
}