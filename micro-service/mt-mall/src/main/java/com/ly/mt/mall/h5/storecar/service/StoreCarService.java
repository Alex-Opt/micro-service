package com.ly.mt.mall.h5.storecar.service;

import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 收藏车接口
 */
public interface StoreCarService {

    /**
     * 加入收藏车
     *
     * @param skuId，num
     * @return
     * @throws Exception
     */
    ResponseJson addStoreCar(String skuId, String num) throws Exception;

    /**
     * 删除收藏车商品
     *
     * @param skuId
     * @return
     * @throws Exception
     */
    ResponseJson subStoreCarSku(String skuId) throws Exception;


    /**
     * 获取用户收藏车信息
     *
     * @return
     * @throws Exception
     */
    ResponseJson getStoreCar() throws Exception;


}
