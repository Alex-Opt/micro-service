package com.ly.mt.core.data.shop.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.shop.dao.ShopInfoDao;
import com.ly.mt.core.data.shop.entity.ShopInfo;
import com.ly.mt.core.data.shop.mapper.ShopInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_SHOP_INFO_ENTITY_ID;

/**
 * ShopInfo操作接口
 *
 * @author taoye
 */
@Service
public class ShopInfoDaoImpl extends BaseDaoServiceImpl implements ShopInfoDao {
    @Resource
    private ShopInfoMapper mapper;

    @Override
    public ShopInfo getShopInfoByIdFromRedis(String id) {
        Assert.notNull(id, "ShopInfoDaoImpl.getShopInfoByIdFromRedis id must not be null");
        String shopInfoJson = redisService.get(REDIS_SHOP_INFO_ENTITY_ID, id);
        if (StringUtil.isNotEmpty(shopInfoJson)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(shopInfoJson), ShopInfo.class);
        }
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setId(id);
        shopInfo = mapper.getShopInfo(shopInfo);
        if (null != shopInfo) {
            redisService.setEntity(REDIS_SHOP_INFO_ENTITY_ID, id, shopInfo);
            return shopInfo;
        } else {
            return new ShopInfo();
        }
    }

    @Override
    public List<ShopInfo> listShopInfoFromMysql(ShopInfo shopInfo) {
        return mapper.listShopInfo(shopInfo);
    }
}