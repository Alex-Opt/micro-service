package com.ly.mt.home.tob.purchases.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.core.redis.RedisKey;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.purchases.service.ShopExclusiveDiscountService;
import com.ly.mt.home.tob.purchases.vo.ShopExclusiveDiscountVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 专属优惠实现类
 *
 * @author: linan
 * @date: 2019/9/10
 **/
@Service
public class ShopExclusiveDiscountServiceImpl extends BaseServiceImpl implements ShopExclusiveDiscountService {

    @Override
    public ShopExclusiveDiscountVo getShopExclusiveDiscount() {
        String shopId = getLoginShopId();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shopId", shopId);
        String result = redisService.get(RedisKey.REDIS_SHOP_EXCLUSIVE_DISCOUNT_ENTITY_SHOP_ID, shopId);
        if(StringUtils.isEmpty(result)) {
            result = callDataCenter(DataCenterMethod.SHOP_EXCLUSIVE_DISCOUNT_GET, jsonObject);
            redisService.set(RedisKey.REDIS_SHOP_EXCLUSIVE_DISCOUNT_ENTITY_SHOP_ID, shopId);
        }

        return JSONObject.parseObject(result, ShopExclusiveDiscountVo.class);
    }
}
