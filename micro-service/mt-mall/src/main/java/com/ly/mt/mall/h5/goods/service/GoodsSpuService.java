package com.ly.mt.mall.h5.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 商品spu业务层
 */
public interface GoodsSpuService {


    /**
     * 修改商品spu数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    ResponseJson queryGoodsSpuById(String id) throws Exception;

    /**
     * 根据类目查询商品spu数据
     *
     * @param
     * @return
     * @throws Exception
     */
    ResponseJson queryGoodsSpuByCategroy(String cid,int page,int rows) throws Exception;

    /**
     * 根据雾化单类目查询商品spu数据
     * @param
     * @return
     * @throws Exception
     */
    ResponseJson queryGoodsSpuByAerosol(int page,int rows) throws  Exception;

    /**
     * 查询商品类目数据
     *
     * @param parentId
     * @return
     * @throws Exception
     */
    ResponseJson queryCategroyList(String parentId) throws Exception;


    /**
     * 查询一小时达商品spu列表
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    ResponseJson queryHourSpu(int page,int rows) throws  Exception;

    /**
     * 查询spu商品数据，包括图片，属性值
     *
     * @param id
     * @return
     * @throws Exception
     */
    ResponseJson queryGoodsHourSpuById(String id) throws Exception;

    /**
     * 统计spuId的商品月销量
     * @param spuId
     * @return
     * @throws Exception
     */
    ResponseJson countGoodsSellerEachMonth(String spuId) throws Exception;

    /**
     *[查询指定功能配置的商品列表]
     * @param systemUserType 面向功能类型:1-到家C实名认证后的商品展示页
     * @return
     * @throws Exception
     */
    ResponseJson querySpuConfigGoodsByUserType(String systemUserType) throws Exception;
}
