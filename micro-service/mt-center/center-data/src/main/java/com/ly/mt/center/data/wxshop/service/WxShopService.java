package com.ly.mt.center.data.wxshop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface WxShopService {
    /**
     * @Description 保存WxShop
     * @Author wanghongliang
     */
    ResponseJson insertWxShop(JSONObject jsonObject);
    /**
     * @Description 查询WxShop
     * @Author wanghongliang
     */
    ResponseJson getWxShopList(JSONObject jsonObject);
}