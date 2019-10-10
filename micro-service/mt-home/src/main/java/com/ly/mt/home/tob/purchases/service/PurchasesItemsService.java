package com.ly.mt.home.tob.purchases.service;

import com.ly.mt.home.tob.purchases.vo.ShopPurchasesItemsVo;

import java.util.List;

/**
 * 进货商品接口
 *
 * @author: linan
 * @date: 2019/9/10
 **/
public interface PurchasesItemsService {

    /**
     * 添加进货商品
     *
     * @param vo 进货商品
     */
    void addPurchasesItems(ShopPurchasesItemsVo vo);

    /**
     * 商家进货累计数
     * @param shopId
     * @return
     */
    Integer getShopItemNum(String shopId);

    /**
     * spu销量
     * @param spuId
     * @return
     */
    Integer getSaleNum(String spuId);
}
