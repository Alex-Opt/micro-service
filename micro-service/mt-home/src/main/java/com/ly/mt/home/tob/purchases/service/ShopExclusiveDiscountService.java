package com.ly.mt.home.tob.purchases.service;

import com.ly.mt.home.tob.purchases.vo.ShopExclusiveDiscountVo;

/**
 * 专属优惠接口
 *
 * @author: linan
 * @date: 2019/9/10
 **/
public interface ShopExclusiveDiscountService {

    /**
     * 商家专属优惠
     *
     * @return ShopExclusiveDiscountVo
     */
    ShopExclusiveDiscountVo getShopExclusiveDiscount();
}
