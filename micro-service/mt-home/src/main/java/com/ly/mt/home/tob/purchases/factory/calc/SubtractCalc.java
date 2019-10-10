package com.ly.mt.home.tob.purchases.factory.calc;

import com.ly.mt.home.tob.purchases.vo.ShopPurchasesVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * 满减计算
 *
 * @author: linan
 * @date: 2019/09/11
 **/
public class SubtractCalc extends AbstractCalc {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    // TODO: 2019/9/17 to coding
    @Override
    public ShopPurchasesVo calc(ShopPurchasesVo shopPurchasesVo, BigDecimal minuend) {
        logger.info("subtract method minuend is {}", minuend);
        return null;
    }
}
