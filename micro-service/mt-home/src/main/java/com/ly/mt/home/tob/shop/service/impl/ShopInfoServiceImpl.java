/*
package com.ly.mt.home.tob.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.shop.service.ShopInfoService;
import com.ly.mt.home.tob.shop.vo.ShopInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShopInfoServiceImpl extends BaseServiceImpl implements ShopInfoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ShopInfoVo getShopInfo(String id, String mobile, String userId) throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("mobile", mobile);
        jsonObject.put("userId", userId);
        ShopInfoVo shopInfoVo = JSONObject.parseObject(callDataCenter(DataCenterMethod.SHOP_INFO_GET, jsonObject),ShopInfoVo.class);
        return shopInfoVo;
    }
}
*/
