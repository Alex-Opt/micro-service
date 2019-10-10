package com.ly.mt.center.data.share.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface ShareGoodsService {
    /**
     * @Description 保存ShareGoods
     * @Author taoye
     */
    ResponseJson insertShareGoods(JSONObject jsonObject);

    /**
     * @Description 删除ShareGoods
     * @Author taoye
     */
    ResponseJson deleteShareGoods(JSONObject jsonObject);

    /**
     * @Description 更新ShareGoods
     * @Author taoye
     */
    ResponseJson updateShareGoods(JSONObject jsonObject);

    /**
     * @Description 查询ShareGoods
     * @Author taoye
     */
    ResponseJson getShareGoods(JSONObject jsonObject);
}