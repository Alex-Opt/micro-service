package com.ly.mt.home.tob.purchases.factory.decorator;

import com.ly.mt.home.base.util.ApplicationContextHelper;
import com.ly.mt.home.tob.ladder.service.LadderService;
import com.ly.mt.home.tob.ladder.vo.LadderVo;
import com.ly.mt.home.tob.purchases.factory.DiscountFactory;
import com.ly.mt.home.tob.purchases.vo.ShopPurchasesVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @author: linan
 * @date: 2019/09/11
 **/
public class LadderDecorator extends AbstractPriceComponent {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private LadderService ladderService = ApplicationContextHelper.getBean(LadderService.class);

    private AbstractPriceComponent abstractDiscount;

    public LadderDecorator(AbstractPriceComponent abstractDiscount) {
        this.discountType = DiscountFactory.TYPE_LADDER;
        this.abstractDiscount = abstractDiscount;
    }

    @Override
    public ShopPurchasesVo calc(ShopPurchasesVo shopPurchasesVo) {
        if(abstractDiscount != null) {
            abstractDiscount.calc(shopPurchasesVo);
        }

        Integer num = shopPurchasesVo.getTotalSkuNum();
        LadderVo ladderVo = ladderService.getCurrentLadder(num);
        logger.info("ladder info:{} ", ladderVo.toString());

        percentages = new BigDecimal(ladderVo.getPromotionRate());

        remarks = ladderVo.getPurachasesNum().concat("件").
                    concat(String.valueOf(percentages.multiply(new BigDecimal("10")).stripTrailingZeros().toPlainString())).concat("折");

        shopPurchasesVo = super.multiply(shopPurchasesVo);

        return shopPurchasesVo;
    }

}
