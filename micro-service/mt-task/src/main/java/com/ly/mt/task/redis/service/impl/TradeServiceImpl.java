package com.ly.mt.task.redis.service.impl;

import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.order.dao.OrderHbsViewDao;
import com.ly.mt.core.data.order.dao.OrderHcViewDao;
import com.ly.mt.core.data.redis.entity.RedisRefresh;
import com.ly.mt.core.data.trade.dao.TradeOrderCouponDao;
import com.ly.mt.core.data.trade.dao.TradeOrderItemsDao;
import com.ly.mt.core.data.trade.dao.TradeOrdersDao;
import com.ly.mt.task.base.service.impl.BaseServiceImpl;
import com.ly.mt.task.redis.service.TradeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.dict.TriggerType.TRIGGER_TYPE_DELETE;
import static com.ly.mt.core.redis.RedisKey.*;

/**
 * shop_* 缓存更新处理
 *
 * @author taoye
 */
@Service
public class TradeServiceImpl extends BaseServiceImpl implements TradeService {
    @Resource
    private TradeOrdersDao tradeOrdersDao;
    @Resource
    private TradeOrderItemsDao tradeOrderItemsDao;
    @Resource
    private TradeOrderCouponDao tradeOrderCouponDao;
    @Resource
    private OrderHbsViewDao orderHbsViewDao;
    @Resource
    private OrderHcViewDao orderHcViewDao;

    @Override
    public void refreshTradeOrders(RedisRefresh refresh) throws Exception {
        String id = refresh.getId1();
        if (StringUtil.isNotEmpty(id)) {
            redisService.del(REDIS_TRADE_ORDERS_ENTITY_ID, id);
            redisService.del(REDIS_TRADE_ORDER_ITEMS_LIST_ORDER_ID, id);
            redisService.del(REDIS_ORDER_HBS_VIEW_ENTITY_ID, id);
            redisService.del(REDIS_ORDER_HC_VIEW_ENTITY_ID, id);
            if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
                tradeOrdersDao.getTradeOrdersByIdFromRedis(id);
                tradeOrderItemsDao.listTradeOrderItemsByOrderIdFromRedis(id);
                orderHbsViewDao.getOrderHbsViewByIdFromRedis(id);
                orderHcViewDao.getOrderHcViewByIdFromRedis(id);
            }
        } else {
            throw new RuntimeException("TradeServiceImpl.refreshTradeOrders refresh.id1 must not be null");
        }
    }


    @Override
    public void refreshTradeOrderItems(RedisRefresh refresh) throws Exception {
        String id = refresh.getId1();
        if (StringUtil.isNotEmpty(id)) {
            redisService.del(REDIS_TRADE_ORDER_ITEMS_LIST_ORDER_ID, id);
            if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
                tradeOrderItemsDao.listTradeOrderItemsByOrderIdFromRedis(id);
            }
        } else {
            throw new RuntimeException("TradeServiceImpl.refreshTradeOrderItems refresh.id1 must not be null");
        }
    }


    @Override
    public void refreshTradeOrderCoupon(RedisRefresh refresh) {
        String couponType = refresh.getId1();
        String orderId = refresh.getId2();
        if (StringUtil.isNotEmpty(couponType) && StringUtil.isNotEmpty(orderId)) {
            redisService.del(REDIS_TRADE_ORDER_COUPON_LIST_COUPON_TYPE_ORDER_ID, couponType + ":" + orderId);
            if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
                tradeOrderCouponDao.listTradeOrderCouponByOrderIdAndCouponTypeFromRedis(orderId, couponType);
            }
        } else {
            throw new RuntimeException("TradeServiceImpl.refreshTradeOrderItems refresh.id1 refresh.id2 must not be null");
        }
    }
}