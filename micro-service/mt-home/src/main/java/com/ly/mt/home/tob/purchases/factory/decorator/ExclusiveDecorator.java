package com.ly.mt.home.tob.purchases.factory.decorator;

import com.ly.mt.home.base.constant.ShopConstant;
import com.ly.mt.home.base.util.ApplicationContextHelper;
import com.ly.mt.home.tob.purchases.factory.DiscountFactory;
import com.ly.mt.home.tob.purchases.service.ShopExclusiveDiscountService;
import com.ly.mt.home.tob.purchases.vo.ShopExclusiveDiscountVo;
import com.ly.mt.home.tob.purchases.vo.ShopPurchasesVo;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * @author: linan
 * @date: 2019/09/11
 **/
public class ExclusiveDecorator extends AbstractPriceComponent {

    private ShopExclusiveDiscountService shopExclusiveDiscountService = ApplicationContextHelper.getBean(ShopExclusiveDiscountService.class);

    private AbstractPriceComponent abstractDiscount;

    public ExclusiveDecorator(AbstractPriceComponent abstractDiscount) {
        this.discountType = DiscountFactory.TYPE_EXCLUSIVE;
        this.abstractDiscount = abstractDiscount;
    }

    @Override
    public ShopPurchasesVo calc(ShopPurchasesVo shopPurchasesVo) {
        if(abstractDiscount != null) {
            abstractDiscount.calc(shopPurchasesVo);
        }

        ShopExclusiveDiscountVo shopExclusiveDiscountVo = shopExclusiveDiscountService.getShopExclusiveDiscount();

        Assert.notNull(shopExclusiveDiscountVo, "专属折扣已失效，请重新下单");
        Assert.isTrue(ShopConstant.YesOrNo.YES.getValue().equals(shopExclusiveDiscountVo.getIsEnable()),
                "专属折扣已失效，请重新下单");

        // TODO: 2019/9/17 to coding enum
        if(shopExclusiveDiscountVo.getCategory().equals("1")) {
            percentages = new BigDecimal(shopExclusiveDiscountVo.getDiscountRate());
            remarks = String.valueOf(percentages.multiply(new BigDecimal("10")).stripTrailingZeros().toPlainString()).concat("折");

            shopPurchasesVo = multiply(shopPurchasesVo);
        } else if(shopExclusiveDiscountVo.getCategory().equals("2")
                && new BigDecimal(shopExclusiveDiscountVo.getDiscountCashLimit()).compareTo(
                        new BigDecimal(shopPurchasesVo.getActualAmount())) >= 0) {
            minuend = new BigDecimal(shopExclusiveDiscountVo.getDiscountCash());
            remarks = "减".concat(String.valueOf(minuend.stripTrailingZeros().toPlainString())).concat("元");

            shopPurchasesVo = subtract(shopPurchasesVo);
        }
        return shopPurchasesVo;
    }
}
