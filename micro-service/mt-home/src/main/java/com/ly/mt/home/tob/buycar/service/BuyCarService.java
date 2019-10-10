package com.ly.mt.home.tob.buycar.service;


import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.home.tob.buycar.vo.BuyCar;
import com.ly.mt.home.tob.buycar.vo.CarSku;

import java.util.List;

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
    ResponseJson addCar(List<CarSku> list);

    /**
     * 购物车减少商品数量
     *
     * @param
     * @return
     * @throws Exception
     */
    ResponseJson subCarSkuNum(String skuId, String num);

    /**
     * 删除购物车商品
     *
     * @param skuId
     * @return
     * @throws Exception
     */
    ResponseJson subCarSku(String skuId);


    /**
     * 获取用户购物车信息
     *
     * @param
     * @return
     * @throws Exception
     */
    ResponseJson getBuyCar();

    /**
     * 获取购物车基本信息
     *
     * @return
     * @throws Exception
     */
    BuyCar getBuyCarInfo();

}
