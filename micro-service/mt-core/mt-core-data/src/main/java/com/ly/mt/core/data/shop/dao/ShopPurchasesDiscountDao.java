package com.ly.mt.core.data.shop.dao;

import com.ly.mt.core.data.shop.entity.ShopPurchasesDiscount;

import java.util.List;

/**
 * ShopPurchasesDiscount操作接口
 *
 * @author taoye
 */
public interface ShopPurchasesDiscountDao {
    /**
     * 从mysql查询List<ShopPurchasesDiscount>
     *
     * @param shopPurchasesDiscount 查询条件
     * @return List<ShopPurchasesDiscount>
     * @author taoye
     */
    List<ShopPurchasesDiscount> listShopPurchasesDiscount(ShopPurchasesDiscount shopPurchasesDiscount);
}