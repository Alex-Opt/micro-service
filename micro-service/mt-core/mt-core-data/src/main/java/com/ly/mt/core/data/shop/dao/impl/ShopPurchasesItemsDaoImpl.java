package com.ly.mt.core.data.shop.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.shop.dao.ShopPurchasesItemsDao;
import com.ly.mt.core.data.shop.entity.ShopPurchasesItems;
import com.ly.mt.core.data.shop.mapper.ShopPurchasesItemsMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_SHOP_PURCHASES_ITEMS_LIST_SHOP_PURCHASES_ID;

/**
 * ShopPurchasesItems操作接口
 *
 * @author taoye
 */
@Service
public class ShopPurchasesItemsDaoImpl extends BaseDaoServiceImpl implements ShopPurchasesItemsDao {
    @Resource
    private ShopPurchasesItemsMapper mapper;

    @Override
    public List<ShopPurchasesItems> listShopPurchasesItemsByShopPurchasesIdFromRedis(String shopPurchaseId) {
        String json = redisService.get(REDIS_SHOP_PURCHASES_ITEMS_LIST_SHOP_PURCHASES_ID, shopPurchaseId);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.parseObject(json, new TypeReference<List<ShopPurchasesItems>>() {
            });
        }
        Assert.notNull(shopPurchaseId, "ShopPurchasesItemsDaoImpl.listShopPurchasesItemsByOrderIdFromRedis shopPurchaseId must not be null");
        ShopPurchasesItems shopPurchasesItems = new ShopPurchasesItems();
        shopPurchasesItems.setShopPurchasesId(shopPurchaseId);
        List<ShopPurchasesItems> list = mapper.listShopPurchasesItems(shopPurchasesItems);
        if (null != list) {
            redisService.setEntity(REDIS_SHOP_PURCHASES_ITEMS_LIST_SHOP_PURCHASES_ID, shopPurchaseId, list);
            return list;
        } else {
            return new ArrayList<>();
        }
    }
}