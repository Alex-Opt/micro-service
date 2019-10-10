package com.ly.mt.core.data.order.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.order.dao.OrdersBattleInfoDao;
import com.ly.mt.core.data.order.entity.OrdersBattleInfo;
import com.ly.mt.core.data.order.mapper.OrdersBattleInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_ORDERS_BATTLE_INFO_ENTITY_ORDER_ID;

/**
 * OrdersBattleInfo操作接口
 *
 * @author taoye
 */
@Service
public class OrdersBattleInfoDaoImpl extends BaseDaoServiceImpl implements OrdersBattleInfoDao {
    @Resource
    private OrdersBattleInfoMapper mapper;

    @Override
    public OrdersBattleInfo getOrdersBattleInfoByOrderIdFromRedis(String orderId) {
        Assert.notNull(orderId, "OrdersBattleInfoDaoImpl.getOrdersBattleInfoByOrderIdFromRedis orderId must not be null");
        String json = redisService.get(REDIS_ORDERS_BATTLE_INFO_ENTITY_ORDER_ID, orderId);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(json), OrdersBattleInfo.class);
        }
        OrdersBattleInfo info = new OrdersBattleInfo();
        info.setOrderId(orderId);
        info = mapper.getOrdersBattleInfo(info);
        if (null != info) {
            redisService.setEntity(REDIS_ORDERS_BATTLE_INFO_ENTITY_ORDER_ID, orderId, info);
            return info;
        } else {
            return new OrdersBattleInfo();
        }
    }

    @Override
    public List<OrdersBattleInfo> listOrdersBattleInfoFromMysql(OrdersBattleInfo ordersBattleInfo) {
        return mapper.listOrdersBattleInfo(ordersBattleInfo);
    }

    @Override
    public int updateOrdersBattleInfo(OrdersBattleInfo ordersBattleInfo) {
        Assert.notNull(ordersBattleInfo, "OrdersBattleInfoDaoImpl.updateOrdersBattleInfo ordersBattleInfo must not be null");
        Assert.notNull(ordersBattleInfo.getOrderId(), "OrdersBattleInfoDaoImpl.updateOrdersBattleInfo ordersBattleInfo.orderId must not be null");
        return mapper.updateOrdersBattleInfo(ordersBattleInfo);
    }
}