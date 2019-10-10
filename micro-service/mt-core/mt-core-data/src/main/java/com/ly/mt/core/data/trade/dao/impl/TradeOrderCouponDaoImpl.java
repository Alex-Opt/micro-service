package com.ly.mt.core.data.trade.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.dict.CouponType;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.trade.dao.TradeOrderCouponDao;
import com.ly.mt.core.data.trade.entity.TradeOrderCoupon;
import com.ly.mt.core.data.trade.mapper.TradeOrderCouponMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_TRADE_ORDER_COUPON_LIST_COUPON_TYPE_ORDER_ID;

/**
 * TradeOrderCoupon操作接口
 *
 * @author taoye
 */
@Service
public class TradeOrderCouponDaoImpl extends BaseDaoServiceImpl implements TradeOrderCouponDao {
    @Resource
    private TradeOrderCouponMapper mapper;

    @Override
    public List<TradeOrderCoupon> listTradeOrderCouponByOrderIdAndCouponTypeFromRedis(String orderId, String couponType) {
        Assert.notNull(orderId, "TradeOrderCouponDaoImpl.listTradeOrderCouponByOrderIdAndCouponTypeFromRedis orderId must not be null");
        String listJson = redisService.get(REDIS_TRADE_ORDER_COUPON_LIST_COUPON_TYPE_ORDER_ID, couponType + ":" + orderId);
        if (StringUtil.isNotEmpty(listJson)) {
            return JSONObject.parseObject(listJson, new TypeReference<List<TradeOrderCoupon>>() {
            });
        }
        TradeOrderCoupon tradeOrderCoupon = new TradeOrderCoupon();
        tradeOrderCoupon.setOrderId(orderId);
        tradeOrderCoupon.setCouponType(couponType);
        List<TradeOrderCoupon> list = mapper.listTradeOrderCoupon(tradeOrderCoupon);
        if (null != list) {
            redisService.setEntity(REDIS_TRADE_ORDER_COUPON_LIST_COUPON_TYPE_ORDER_ID, couponType + ":" + orderId, list);
            return list;
        } else {
            return new ArrayList<>();
        }
    }
}