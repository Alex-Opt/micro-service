package com.ly.mt.home.tob.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopPurchasesItemsService {
    /**
     * 进货订单列表
     *
     * @param parameter
     * @return
     */
    ResponseJson getItemList(JSONObject parameter);
}
