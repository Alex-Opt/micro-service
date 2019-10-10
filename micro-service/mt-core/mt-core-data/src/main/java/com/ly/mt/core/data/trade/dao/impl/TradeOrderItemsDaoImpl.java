package com.ly.mt.core.data.trade.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.trade.dao.TradeOrderItemsDao;
import com.ly.mt.core.data.trade.entity.TradeOrderItems;
import com.ly.mt.core.data.trade.mapper.TradeOrderItemsMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_TRADE_ORDER_ITEMS_LIST_ORDER_ID;

/**
 * TradeOrderItems操作接口
 *
 * @author taoye
 */
@Service
public class TradeOrderItemsDaoImpl extends BaseDaoServiceImpl implements TradeOrderItemsDao {
    @Resource
    private TradeOrderItemsMapper mapper;

    @Override
    public List<TradeOrderItems> listTradeOrderItemsByOrderIdFromRedis(String orderId) {
        Assert.notNull(orderId, "TradeOrderItemsDaoImpl.listTradeOrderItemsByOrderIdFromRedis orderId must not be null");
        String tradeOrderItemsJson = redisService.get(REDIS_TRADE_ORDER_ITEMS_LIST_ORDER_ID, orderId);
        if (StringUtil.isNotEmpty(tradeOrderItemsJson)) {
            return JSONObject.parseObject(tradeOrderItemsJson, new TypeReference<List<TradeOrderItems>>() {
            });
        }
        TradeOrderItems tradeOrderItems = new TradeOrderItems();
        tradeOrderItems.setOrderId(orderId);
        List<TradeOrderItems> list = mapper.listTradeOrderItems(tradeOrderItems);
        if (null != list && list.size() > 0) {
            redisService.setEntity(REDIS_TRADE_ORDER_ITEMS_LIST_ORDER_ID, orderId, list);
            return list;
        } else {
            return new ArrayList<>();
        }
    }
}