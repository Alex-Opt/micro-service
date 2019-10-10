package com.ly.mt.task.redis.service.impl;

import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.order.dao.OrderHbpViewDao;
import com.ly.mt.core.data.redis.entity.RedisRefresh;
import com.ly.mt.core.data.shop.dao.ShopInfoDao;
import com.ly.mt.core.data.shop.dao.ShopPurchasesItemsDao;
import com.ly.mt.task.base.service.impl.BaseServiceImpl;
import com.ly.mt.task.redis.service.ShopService;
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
public class ShopServiceImpl extends BaseServiceImpl implements ShopService {
    @Resource
    private ShopPurchasesItemsDao shopPurchasesItemsDao;
    @Resource
    private OrderHbpViewDao orderHbpViewDao;
    @Resource
    private ShopInfoDao shopInfoDao;

    @Override
    public void refreshShopPurchases(RedisRefresh refresh) throws Exception {
        String id = refresh.getId1();
        if (StringUtil.isNotEmpty(id)) {
            redisService.del(REDIS_ORDER_HBP_VIEW_ENTITY_ID, id);
            redisService.del(REDIS_SHOP_PURCHASES_ITEMS_LIST_SHOP_PURCHASES_ID, id);
            if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
                shopPurchasesItemsDao.listShopPurchasesItemsByShopPurchasesIdFromRedis(id);
                orderHbpViewDao.getOrderHbpViewByIdFromRedis(id);
            }
        } else {
            throw new RuntimeException("ShopServiceImpl.refreshShopPurchases refresh.id1 must not be null");
        }
    }


    @Override
    public void refreshShopPurchasesItems(RedisRefresh refresh) throws Exception {
        String id = refresh.getId1();
        if (StringUtil.isNotEmpty(id)) {
            redisService.del(REDIS_SHOP_PURCHASES_ITEMS_LIST_SHOP_PURCHASES_ID, id);
            if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
                shopPurchasesItemsDao.listShopPurchasesItemsByShopPurchasesIdFromRedis(id);
            }
        } else {
            throw new RuntimeException("ShopServiceImpl.refreshShopPurchasesItems refresh.id1 must not be null");
        }
    }


    @Override
    public void refreshShopInfo(RedisRefresh refresh) throws Exception {
        String id = refresh.getId1();
        if (StringUtil.isNotEmpty(id)) {
            redisService.del(REDIS_SHOP_INFO_ENTITY_ID, id);
            if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
                shopInfoDao.getShopInfoByIdFromRedis(id);
            }
        } else {
            throw new RuntimeException("ShopServiceImpl.refreshShopInfo refresh.id1 must not be null");
        }
    }
}