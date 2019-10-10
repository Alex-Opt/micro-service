package com.ly.mt.home.tob.stocks.service;

import com.ly.mt.home.tob.stocks.vo.ShopStocksVo;

/**
 * @description: 商家库存
 * @author: linan
 * @date: 2019/7/17
 */
public interface ShopStocksService {
    ShopStocksVo getStock(String shopId, String spuId);

    void addStock(ShopStocksVo shopStocksVo);

    void updateStock(ShopStocksVo shopStocksVo);

}
