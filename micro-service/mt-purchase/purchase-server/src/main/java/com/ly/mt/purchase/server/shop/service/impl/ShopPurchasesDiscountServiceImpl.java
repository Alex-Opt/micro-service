package com.ly.mt.purchase.server.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.purchase.ShopPurchasesDiscount;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.purchase.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.purchase.server.shop.mapper.ShopPurchasesDiscountMapper;
import com.ly.mt.purchase.server.shop.service.ShopPurchasesDiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商家进货优惠具体实现
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-16 22:59:59
 */
@Service
public class ShopPurchasesDiscountServiceImpl extends BaseServiceImpl implements ShopPurchasesDiscountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopPurchasesDiscountServiceImpl.class);

    @Resource
    private ShopPurchasesDiscountMapper shopPurchasesDiscountMapper;

    /**
     * B端 查询当前优惠信息
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject getPurchasesDiscountInfoByShopId(String json) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String shopId = getLoginShopId();
        List<ShopPurchasesDiscount> shopPurchasesDiscountList = shopPurchasesDiscountMapper.selectByShopId(Long.parseLong(shopId));
        double stairPriceDisCount = 0.0;
        double memberPriceDisCount = 0.0;
        // 进货总优惠金额
        double allPriceDiscount = 0.0;
        for(ShopPurchasesDiscount purchasesDisCount : shopPurchasesDiscountList) {
            if(!"1".equals(purchasesDisCount.getStatus())) {
                continue;
            }
            if("1".equals(purchasesDisCount.getDiscountType())) {
                // 阶梯价优惠
                stairPriceDisCount += Double.parseDouble(purchasesDisCount.getDiscountPrice());
            } else if ("2".equals(purchasesDisCount.getDiscountType())) {
                // 会员价优惠
                memberPriceDisCount += Double.parseDouble(purchasesDisCount.getDiscountPrice());
            } else {
                // 不做任何处理
            }
            allPriceDiscount += Double.parseDouble(purchasesDisCount.getDiscountPrice());
        }
        jsonObject.put("stairPriceDisCount",stairPriceDisCount);
        jsonObject.put("memberPriceDisCount",memberPriceDisCount);
        jsonObject.put("allPriceDiscount",allPriceDiscount);
        return JsonUtil.getSuccessJson(jsonObject);
    }
}
