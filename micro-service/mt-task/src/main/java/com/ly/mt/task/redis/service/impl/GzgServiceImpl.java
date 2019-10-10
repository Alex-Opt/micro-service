package com.ly.mt.task.redis.service.impl;

import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.gzg.dao.GzgHotelDao;
import com.ly.mt.core.data.gzg.dao.GzgInfoDao;
import com.ly.mt.core.data.gzg.dao.GzgOrderDao;
import com.ly.mt.core.data.gzg.dao.GzgOrderItemDao;
import com.ly.mt.core.data.redis.entity.RedisRefresh;
import com.ly.mt.task.base.service.impl.BaseServiceImpl;
import com.ly.mt.task.redis.service.GzgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.dict.TriggerType.TRIGGER_TYPE_DELETE;
import static com.ly.mt.core.redis.RedisKey.*;

/**
 * gzg_* 缓存更新处理
 *
 * @author taoye
 */
@Service
public class GzgServiceImpl extends BaseServiceImpl implements GzgService {
    @Resource
    private GzgHotelDao gzgHotelDao;
    @Resource
    private GzgInfoDao gzgInfoDao;
    @Resource
    private GzgOrderDao gzgOrderDao;
    @Resource
    private GzgOrderItemDao gzgOrderItemDao;


    @Override
    public void refreshGzgHotel(RedisRefresh refresh) throws Exception {
        String id = refresh.getId1();
        if (StringUtil.isNotEmpty(id)) {
            redisService.del(REDIS_GZG_HOTEL_ENTITY_ID, id);
            if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
                gzgHotelDao.getGzgHotelByIdFromRedis(id);
            }
        } else {
            throw new RuntimeException("GzgServiceImpl.refreshGzgHotel refresh.id1 must not be null");
        }
    }


    @Override
    public void refreshGzgInfo(RedisRefresh refresh) throws Exception {
        String code = refresh.getId1();
        if (StringUtil.isNotEmpty(code)) {
            redisService.del(REDIS_GZG_INFO_ENTITY_CODE, code);
            if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
                gzgInfoDao.getGzgInfoByCodeFromRedis(code);
            }
        } else {
            throw new RuntimeException("GzgServiceImpl.refreshGzgInfo refresh.id1 must not be null");
        }
    }


    @Override
    public void refreshGzgOrder(RedisRefresh refresh) throws Exception {
        String id = refresh.getId1();
        if (StringUtil.isNotEmpty(id)) {
            redisService.del(REDIS_GZG_ORDER_ENTITY_ID, id);
            redisService.del(REDIS_GZG_ORDER_ITEM_LIST_ORDER_ID, id);
            if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
                gzgOrderDao.getGzgOrderByIdFromRedis(id);
                gzgOrderItemDao.listGzgOrderItemByOrderIdFromRedis(id);
            }
        } else {
            throw new RuntimeException("GzgServiceImpl.refreshGzgOrder refresh.id1 must not be null");
        }
    }


    @Override
    public void refreshGzgOrderItem(RedisRefresh refresh) throws Exception {
        String orderId = refresh.getId1();
        if (StringUtil.isNotEmpty(orderId)) {
            redisService.del(REDIS_GZG_ORDER_ITEM_LIST_ORDER_ID, orderId);
            if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
                gzgOrderItemDao.listGzgOrderItemByOrderIdFromRedis(orderId);
            }
        } else {
            throw new RuntimeException("GzgServiceImpl.refreshGzgOrderItem refresh.id1 must not be null");
        }
    }
}