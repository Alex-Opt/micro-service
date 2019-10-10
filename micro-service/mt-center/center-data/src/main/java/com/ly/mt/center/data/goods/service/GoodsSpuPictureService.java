package com.ly.mt.center.data.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GoodsSpuPictureService {
    /**
     * @Description 插入GoodsSpuPicture
     * @Author taoye
     */
    ResponseJson insertGoodsSpuPicture(JSONObject jsonObject);

    /**
     * @Description 根据id删除GoodsSpuPicture
     * @Author taoye
     */
    ResponseJson deleteGoodsSpuPictureById(JSONObject jsonObject);

    /**
     * @Description 根据id更新GoodsSpuPicture
     * @Author taoye
     */
    ResponseJson updateGoodsSpuPictureById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询GoodsSpuPicture
     * @Author taoye
     */
    ResponseJson getGoodsSpuPicture(JSONObject jsonObject);

    /**
     * @Description 根据id查询GoodsSpuPicture
     * @Author taoye
     */
    ResponseJson getGoodsSpuPictureById(JSONObject jsonObject);

    /**
     * 根据spu id查询商品图片数据
     * @param jsonObject
     * @return
     */
    ResponseJson getGoodsSpuPictureBySpuId(JSONObject jsonObject);
}