package com.ly.mt.center.data.wxshop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 微信店铺Web后台
 */
public interface WxShopWebService {
    /**
     * @Description 删除WxShop
     * @Author wanghongliang
     */
    ResponseJson deleteWxShop(JSONObject jsonObject);

    /**
     * @Description 更新WxShop
     * @Author wanghongliang
     */
    ResponseJson updateWxShop(JSONObject jsonObject);

    /**
     * @Description 查询WxShop
     * @Author wanghongliang
     */
    ResponseJson getWxShopList(JSONObject jsonObject);

    /**
     * @Description 获取WxShop
     * @Author wanghongliang
     */
    ResponseJson getWxShop(JSONObject jsonObject);
}