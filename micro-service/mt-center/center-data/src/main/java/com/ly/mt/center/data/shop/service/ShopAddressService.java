package com.ly.mt.center.data.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShopAddressService {
    /**
     * @Description 插入ShopAddress
     * @Author taoye
     */
    ResponseJson insertShopAddress(JSONObject jsonObject);

    /**
     * @Description 根据id删除ShopAddress
     * @Author taoye
     */
    ResponseJson deleteShopAddressById(JSONObject jsonObject);

    /**
     * @Description 根据id更新ShopAddress
     * @Author taoye
     */
    ResponseJson updateShopAddressById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询ShopAddress
     * @Author taoye
     */
    ResponseJson getShopAddress(JSONObject jsonObject);

    /**
     * @Description 根据shopId查询店铺的地址信息
     * @Author zhanglifeng
     * @param jsonObject
     * @return
     */
    ResponseJson getShopDefaultAddressByShopId(JSONObject jsonObject);

    /**
     * @Description 根据条件查询ShopAddress
     * @Author taoye
     */
    ResponseJson getShopAddressList(JSONObject jsonObject);

}