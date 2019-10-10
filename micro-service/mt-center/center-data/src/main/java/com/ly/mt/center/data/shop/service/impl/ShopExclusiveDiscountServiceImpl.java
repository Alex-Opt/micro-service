package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.entity.ShopExclusiveDiscount;
import com.ly.mt.center.data.shop.mapper.ShopExclusiveDiscountMapper;
import com.ly.mt.center.data.shop.service.ShopExclusiveDiscountService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class ShopExclusiveDiscountServiceImpl implements ShopExclusiveDiscountService {

    @Resource
    ShopExclusiveDiscountMapper mapper;

    @Override
    public ResponseJson getShopExclusiveDiscount(JSONObject jsonObject) {
        ShopExclusiveDiscount shopExclusiveDiscount = JSONObject.toJavaObject(jsonObject, ShopExclusiveDiscount.class);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getShopExclusiveDiscount(shopExclusiveDiscount));
    }
}