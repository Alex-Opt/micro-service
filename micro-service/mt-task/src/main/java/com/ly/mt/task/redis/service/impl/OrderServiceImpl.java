package com.ly.mt.task.redis.service.impl;

import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.order.dao.OrdersBattleInfoDao;
import com.ly.mt.core.data.redis.entity.RedisRefresh;
import com.ly.mt.task.base.service.impl.BaseServiceImpl;
import com.ly.mt.task.redis.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.dict.TriggerType.TRIGGER_TYPE_DELETE;
import static com.ly.mt.core.redis.RedisKey.REDIS_ORDERS_BATTLE_INFO_ENTITY_ORDER_ID;

/**
 * order_* 缓存更新处理
 *
 * @author taoye
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {
    @Resource
    private OrdersBattleInfoDao ordersBattleInfoDao;

    @Override
    public void refreshOrdersBattleInfo(RedisRefresh refresh) throws Exception {
        String id = refresh.getId1();
        if (StringUtil.isNotEmpty(id)) {
            redisService.del(REDIS_ORDERS_BATTLE_INFO_ENTITY_ORDER_ID, id);
            if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
                ordersBattleInfoDao.getOrdersBattleInfoByOrderIdFromRedis(id);
            }
        } else {
            throw new RuntimeException("OrderServiceImpl.refreshOrdersBattleInfo refresh.id1 must not be null");
        }
    }
}