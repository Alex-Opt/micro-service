package com.ly.mt.home.tob.purchases.service;

/**
 * 退款接口
 *
 * @author: linan
 * @date: 2019/9/10
 **/
public interface ShopPurchasesRefundItemsService {

    /**
     * 商家累计退货数
     *
     * @param shopId 商家id
     * @return java.lang.Integer
     */
    Integer getShopRefundItemNum(String shopId);
}
