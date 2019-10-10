package com.ly.mt.home.tob.purchases.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.purchases.service.ShopPurchasesRefundItemsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 退款实现类
 *
 * @author: linan
 * @date: 2019/9/10
 **/
@Service
public class ShopPurchasesRefundItemsServiceImpl extends BaseServiceImpl implements ShopPurchasesRefundItemsService {

    @Override
    public Integer getShopRefundItemNum(String shopId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shop_id", shopId);
        String num = callDataCenter(DataCenterMethod.SHOP_PURCHASES_REFUND_ITEM_NUM_GET, jsonObject);
        return StringUtils.isEmpty(num) ? 0 : Integer.parseInt(num);
    }
}
