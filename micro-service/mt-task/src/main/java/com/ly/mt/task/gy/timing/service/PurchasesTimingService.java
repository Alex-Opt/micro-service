package com.ly.mt.task.gy.timing.service;

import com.ly.mt.core.data.shop.entity.ShopPurchases;

import java.util.List;

public interface PurchasesTimingService {

    /**
     * 批量推送订单到管易系统
     * @param purchasesList
     * @return
     * @throws Exception
     */
    void sendTradeOrdersToGY(List<ShopPurchases> purchasesList) throws  Exception;

    /**
     * 查询出未推送商家订单集合
     * @return
     */
    List<ShopPurchases> getPurchasesList();
}
