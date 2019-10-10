package com.ly.mt.core.data.gzg.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.gzg.dao.GzgOrderItemDao;
import com.ly.mt.core.data.gzg.entity.GzgOrderItem;
import com.ly.mt.core.data.gzg.mapper.GzgOrderItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_GZG_ORDER_ITEM_LIST_ORDER_ID;

/**
 * GzgOrderItem操作接口
 *
 * @author taoye
 */
@Service
public class GzgOrderItemDaoImpl extends BaseDaoServiceImpl implements GzgOrderItemDao {
    @Resource
    private GzgOrderItemMapper mapper;


    @Override
    public List<GzgOrderItem> listGzgOrderItemByOrderIdFromRedis(String orderId) {
        Assert.notNull(orderId, "GzgOrderItemDaoImpl.listGzgOrderItemByOrderIdFromRedis orderId must not be null");
        String listJson = redisService.get(REDIS_GZG_ORDER_ITEM_LIST_ORDER_ID, orderId);
        if (StringUtil.isNotEmpty(listJson)) {
            return JSONObject.parseObject(listJson, new TypeReference<List<GzgOrderItem>>() {
            });
        }
        GzgOrderItem gzgOrderItem = new GzgOrderItem();
        gzgOrderItem.setOrderId(orderId);
        List<GzgOrderItem> list = mapper.listGzgOrderItem(gzgOrderItem);
        if (null != list && list.size() > 0) {
            redisService.setEntity(REDIS_GZG_ORDER_ITEM_LIST_ORDER_ID, orderId, list);
            return list;
        } else {
            return new ArrayList<>();
        }
    }
}