package com.ly.mt.center.data.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GoodsBrandInfoService {
    /**
     * @Description 保存GoodsBrandInfo
     * @Author taoye
     */
    ResponseJson insertGoodsBrandInfo(JSONObject jsonObject);

    /**
     * @Description 删除GoodsBrandInfo
     * @Author taoye
     */
    ResponseJson deleteGoodsBrandInfo(JSONObject jsonObject);

    /**
     * @Description 更新GoodsBrandInfo
     * @Author taoye
     */
    ResponseJson updateGoodsBrandInfo(JSONObject jsonObject);

    /**
     * @Description 查询GoodsBrandInfo
     * @Author taoye
     */
    ResponseJson getGoodsBrandInfo(JSONObject jsonObject);
}