package com.ly.mt.center.data.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GoodsSpuInfoService {
    /**
     * @Description 插入GoodsSpuInfo
     * @Author taoye
     */
    ResponseJson insertGoodsSpuInfo(JSONObject jsonObject);

    /**
     * @Description 根据id删除GoodsSpuInfo
     * @Author taoye
     */
    ResponseJson deleteGoodsSpuInfoById(JSONObject jsonObject);

    /**
     * @Description 根据id更新GoodsSpuInfo
     * @Author taoye
     */
    ResponseJson updateGoodsSpuInfoById(JSONObject jsonObject);

    /**
     * @Description 根据条件查询GoodsSpuInfo
     * @Author taoye
     */
    ResponseJson getGoodsSpuInfo(JSONObject jsonObject);

    /**
     * @Description 根据id查询GoodsSpuInfo
     * @Author taoye
     */
    ResponseJson getGoodsSpuInfoById(JSONObject jsonObject);

    /**
     * 根据分类id查询商品spu数据
     * @param jsonObject
     * @return
     */
    ResponseJson getGoodsSpuInfoByCategroy(JSONObject jsonObject);

    /**
     * 根据雾化弹分类id查询商品spu数据
     * @param jsonObject
     * @return
     */
    ResponseJson getGoodsSpuInfoByAerosol(JSONObject jsonObject);


    /**
     * 分页查询一小时达商品信息
     * @param jsonObject
     * @return
     */
    ResponseJson queryHourSpu(JSONObject jsonObject);

    /**
     * 查询商品月销量
     * @param jsonObject
     * @return
     */
    ResponseJson getSpuGoodsSellerNumberEachMonth(JSONObject jsonObject);

    /**
     * 查询top5商品
     * @param jsonObject
     * @return
     */
    ResponseJson getListTop5ByCid(JSONObject jsonObject);

    /**
     * @Description 查询有效的商品数据
     * @Author
     */
    ResponseJson queryGoodsSpuInfo(JSONObject jsonObject);

    /**
     * 到家b-spu列表
     * @param jsonObject
     * @return
     */
    ResponseJson getSpuListForShop(JSONObject jsonObject);

    /**
     * 根据spuId的集合查询出spu商品信息集合
     * @param jsonObject
     * @return
     */
    ResponseJson getGoodsSpuInfoBySpuIdList(JSONObject jsonObject);

}