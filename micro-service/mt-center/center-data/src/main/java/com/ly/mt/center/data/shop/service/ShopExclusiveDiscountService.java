package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @author: linan
 * @date: 2019/9/16
 **/
public interface ShopExclusiveDiscountService {

    ResponseJson getShopExclusiveDiscount(JSONObject jsonObject);

}