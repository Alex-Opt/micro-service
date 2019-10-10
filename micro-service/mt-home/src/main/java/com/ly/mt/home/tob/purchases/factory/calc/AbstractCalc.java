package com.ly.mt.home.tob.purchases.factory.calc;

import com.ly.mt.home.tob.purchases.vo.ShopPurchasesVo;

import java.math.BigDecimal;

/**
 * @author: linan
 * @date: 2019/09/11
 **/
public abstract class AbstractCalc {

    /**
     * 价格计算
     *
     * @param shopPurchasesVo 进货单
     * @return
     */
    public abstract ShopPurchasesVo calc(ShopPurchasesVo shopPurchasesVo, BigDecimal num);
}
