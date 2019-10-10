package com.ly.mt.center.data.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GoodsSkuPictureService {
    /**
     * @Description 插入GoodsSkuPicture
     * @Author taoye
     */
    ResponseJson insertGoodsSkuPicture(JSONObject jsonObject);

    /**
     * @Description 根据id删除GoodsSkuPicture
     * @Author taoye
     */
    ResponseJson deleteGoodsSkuPictureById(JSONObject jsonObject);

    /**
     * @Description 根据id更新GoodsSkuPicture
     * @Author taoye
     */
    ResponseJson updateGoodsSkuPictureById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询GoodsSkuPicture
     * @Author taoye
     */
    ResponseJson getGoodsSkuPicture(JSONObject jsonObject);

    /**
     * @Description 根据id查询GoodsSkuPicture
     * @Author taoye
     */
    ResponseJson getGoodsSkuPictureById(JSONObject jsonObject);

    /**
     * @Description 根据skuId查询GoodsSkuPicture
     * @Author
     */
    ResponseJson getGoodsSkuPictureBySkuId(JSONObject jsonObject);

    /**
     * 根据skuId查询GoodsSkuPicture
     * @param jsonObject
     * @return
     */
    ResponseJson getSkuPictureBySkuId(JSONObject jsonObject);
}