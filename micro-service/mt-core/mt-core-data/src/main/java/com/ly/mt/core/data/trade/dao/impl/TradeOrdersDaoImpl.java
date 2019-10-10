package com.ly.mt.core.data.trade.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.trade.dao.TradeOrdersDao;
import com.ly.mt.core.data.trade.entity.TradeOrders;
import com.ly.mt.core.data.trade.mapper.TradeOrdersMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_TRADE_ORDERS_ENTITY_ID;

/**
 * TradeOrders操作接口
 *
 * @author taoye
 */
@Service
public class TradeOrdersDaoImpl extends BaseDaoServiceImpl implements TradeOrdersDao {
    @Resource
    private TradeOrdersMapper mapper;

    @Override
    public TradeOrders getTradeOrdersByIdFromRedis(String id) {
        Assert.notNull(id, "TradeOrdersDaoImpl.getTradeOrdersByIdFromRedis id must not be null");
        String json = redisService.get(REDIS_TRADE_ORDERS_ENTITY_ID, id);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(json), TradeOrders.class);
        }
        TradeOrders tradeOrders = new TradeOrders();
        tradeOrders.setId(id);
        tradeOrders = mapper.getTradeOrders(tradeOrders);
        if (null != tradeOrders) {
            redisService.setEntity(REDIS_TRADE_ORDERS_ENTITY_ID, id, tradeOrders);
            return tradeOrders;
        } else {
            return new TradeOrders();
        }
    }

    @Override
    public List<TradeOrders> listTradeOrdersFromMysql(TradeOrders tradeOrders) {
        return mapper.listTradeOrders(tradeOrders);
    }

    @Override
    public int updateTradeOrders(TradeOrders tradeOrders) {
        Assert.notNull(tradeOrders, "TradeOrdersDaoImpl.updateTradeOrdersById tradeOrders must not be null");
        Assert.notNull(tradeOrders.getId(), "TradeOrdersDaoImpl.updateTradeOrdersById tradeOrders.id must not be null");
        return mapper.updateTradeOrders(tradeOrders);
    }

    @Override
    public int updateFinishStatus() {
        return mapper.updateFinishStatus();
    }

    @Override
    public int updateCancelStatus(List<TradeOrders> tradeOrders) {
        return mapper.updateCancelStatus(tradeOrders);
    }

    @Override
    public List<TradeOrders> getWillCancelOrderByTime(String startTime, String endTime) {
        return mapper.getWillCancelOrderByTime(startTime, endTime);
    }

    @Override
    public String getOrderIdByOrderNo(Long orderNo) {
        return mapper.getOrderIdByOrderNo(orderNo);
    }
}