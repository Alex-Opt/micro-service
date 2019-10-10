package com.ly.mt.home.tob.ladder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.core.redis.RedisKey;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.discount.service.DiscountService;
import com.ly.mt.home.tob.ladder.service.LadderService;
import com.ly.mt.home.tob.ladder.vo.LadderVo;
import com.ly.mt.home.tob.purchases.service.PurchasesItemsService;
import com.ly.mt.home.tob.purchases.service.ShopPurchasesRefundItemsService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * 阶梯价接口
 *
 * @author linan
 * @date 20190709
 */
@Service
public class LadderServiceImpl extends BaseServiceImpl implements LadderService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    DiscountService discountService;
    @Resource
    PurchasesItemsService purchasesItemsService;
    @Resource
    ShopPurchasesRefundItemsService shopPurchasesRefundItemsService;

    @Override
    public List<LadderVo> list() {
        String result = redisService.get(RedisKey.REDIS_ENTITY_SHOP_LADDER_PRICE);
        if (StringUtils.isEmpty(result)) {
            result = callDataCenter(DataCenterMethod.SHOP_PURACHASES_LADDER_PRICE_LIST, new JSONObject());
            redisService.set(RedisKey.REDIS_ENTITY_SHOP_LADDER_PRICE, result, 24 * 60 * 60, TimeUnit.SECONDS);
        }

        List<LadderVo> list = JSONObject.parseArray(result, LadderVo.class);
        return list;
    }

    @Override
    public LadderVo getCurrentLadder(int num) {
        List<LadderVo> list = null;
        list = this.list();

        Collections.sort(list);
        for (LadderVo ladderVo : list) {
            if (Integer.parseInt(ladderVo.getPurachasesNum()) <= num) {
                return ladderVo;
            }
        }

        return null;
    }

    @Override
    public JSONObject shopLadder() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ladderList", list());
        String shopId = getLoginShopId();
        if(StringUtils.isNotEmpty(shopId)){
            String discount = discountService.totalDiscount();
            Integer totalNum = purchasesItemsService.getShopItemNum(shopId);
            Integer totalRefundNum = 0;
            if(totalNum > 0){
                totalRefundNum = shopPurchasesRefundItemsService.getShopRefundItemNum(shopId);
            }

            jsonObject.put("discount",StringUtils.isEmpty(discount) ? 0 : discount);
            jsonObject.put("num", totalNum - totalRefundNum);
        }
        return jsonObject;
    }
}
