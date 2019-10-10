package com.ly.mt.core.data.shop.dao;

import com.ly.mt.core.data.shop.entity.ShopStocks;

import java.util.List;

/**
 * ShopStocks操作接口
 *
 * @author taoye
 */
public interface ShopStocksDao {
    /**
     * 更新List<ShopStocks>
     *
     * @param list 更新条件和数据
     * @return 更新结果
     * @author taoye
     */
    int updateDeliveryNums(List<ShopStocks> list);

    /**
     * 更新List<ShopStocks>
     *
     * @param list 更新条件和数据
     * @return 更新结果
     * @author taoye
     */
    int updateNums(List<ShopStocks> list);
}