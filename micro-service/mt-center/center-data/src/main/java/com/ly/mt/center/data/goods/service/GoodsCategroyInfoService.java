package com.ly.mt.center.data.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GoodsCategroyInfoService {
    /**
     * @Description 保存GoodsCategroyInfo
     * @Author taoye
     */
    ResponseJson insertGoodsCategroyInfo(JSONObject jsonObject);

    /**
     * @Description 删除GoodsCategroyInfo
     * @Author taoye
     */
    ResponseJson deleteGoodsCategroyInfo(JSONObject jsonObject);

    /**
     * @Description 更新GoodsCategroyInfo
     * @Author taoye
     */
    ResponseJson updateGoodsCategroyInfo(JSONObject jsonObject);

    /**
     * @Description 查询GoodsCategroyInfo
     * @Author taoye
     */
    ResponseJson getGoodsCategroyInfo(JSONObject jsonObject);

    /**
     * 根据上级id查询所有自己类目数据
     * @param jsonObject
     * @return
     */
    ResponseJson getGoodsCategroyInfoByParentId(JSONObject jsonObject);
}