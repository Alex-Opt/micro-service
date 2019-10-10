package com.ly.mt.mall.h5.buycar.service;

import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 购物车接口
 *
 * @author add by ypmu
 * @date 20190520
 */
public interface BuyCarService {

    /**
     * 加入购物车
     *
     * @param
     * @return
     * @throws Exception
     */
    ResponseJson addCar(String skuId, String num) throws Exception;

    /**
     * 购物车减少商品数量
     *
     * @param
     * @return
     * @throws Exception
     */
    ResponseJson subCarSkuNum(String skuId, String num) throws Exception;

    /**
     * 删除购物车商品
     *
     * @param skuId
     * @return
     * @throws Exception
     */
    ResponseJson subCarSku(String skuId) throws Exception;


    /**
     * 获取用户购物车信息
     *
     * @param
     * @return
     * @throws Exception
     */
    ResponseJson getBuyCar() throws Exception;

    /**
     * @return
     * @throws Exception
     */
    ResponseJson syncBuyCar(String carSkuList) throws Exception;

}
