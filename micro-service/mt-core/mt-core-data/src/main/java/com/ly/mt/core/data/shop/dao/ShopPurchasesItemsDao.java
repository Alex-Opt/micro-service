package com.ly.mt.core.data.shop.dao;

import com.ly.mt.core.data.shop.entity.ShopPurchasesItems;

import java.util.List;

/**
 * ShopPurchasesItems操作接口
 *
 * @author taoye
 */
public interface ShopPurchasesItemsDao {
    /**
     * 从reids根据id查询List<ShopPurchasesItems>
     * redis不存在则查询mysql
     *
     * @param shopPurchaseId 进货单ID
     * @return List<ShopPurchasesItems>
     * @author taoye
     */
    List<ShopPurchasesItems> listShopPurchasesItemsByShopPurchasesIdFromRedis(String shopPurchaseId);
}