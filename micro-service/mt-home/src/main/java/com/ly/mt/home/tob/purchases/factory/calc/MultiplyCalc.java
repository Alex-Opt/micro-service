package com.ly.mt.home.tob.purchases.factory.calc;

import com.ly.mt.home.tob.purchases.vo.ShopPurchasesItemsVo;
import com.ly.mt.home.tob.purchases.vo.ShopPurchasesVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

/**
 * 折扣计算
 *
 * @author: linan
 * @date: 2019/09/11
 **/
public class MultiplyCalc extends AbstractCalc {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *
     * @param shopPurchasesVo 进货单
     * @return
     */
    @Override
    public ShopPurchasesVo calc(ShopPurchasesVo shopPurchasesVo, BigDecimal percentages) {
        List<ShopPurchasesItemsVo> itemList = shopPurchasesVo.getItemList();

        // purchases 计算

        // 原价
        BigDecimal amount = new BigDecimal(shopPurchasesVo.getAmount());
        // 实际支付金额
        BigDecimal actualAmount = amount.multiply(percentages).setScale(2, BigDecimal.ROUND_HALF_UP);
        // 优惠金额
        BigDecimal discountAmount = amount.subtract(actualAmount);

        shopPurchasesVo.setActualAmount(String.valueOf(actualAmount));
        shopPurchasesVo.setDiscount(String.valueOf(discountAmount));

        // item 计算

        // 累计实付金额
        BigDecimal paymentPriceTemp = new BigDecimal(0);
        // 累计优惠金额
        BigDecimal discountPriceTemp = new BigDecimal(0);
        for (ShopPurchasesItemsVo itemsVo : itemList) {
            // sku_price
            BigDecimal skuPrice = new BigDecimal(itemsVo.getSkuPrice());
            // sku_num
            BigDecimal skuNum = new BigDecimal(itemsVo.getSkuNum());
            // sku_amount
            BigDecimal skuAmount = skuPrice.multiply(skuNum).multiply(percentages).setScale(2, BigDecimal.ROUND_HALF_UP);
            // sku_discount_amount
            BigDecimal skuDiscountAmount = skuPrice.multiply(skuNum).subtract(skuAmount);

            // 最后一件item
            if (itemList.lastIndexOf(itemsVo) == itemList.size() - 1) {
                skuAmount = actualAmount.subtract(paymentPriceTemp);
                skuDiscountAmount = actualAmount.subtract(discountPriceTemp);
            }

            // sku实付金额
            itemsVo.setPaymentPrice(String.valueOf(skuAmount));
            // 优惠分摊金额
            itemsVo.setPomotionPrice(String.valueOf(skuDiscountAmount));

            // 订单累计实付金额
            paymentPriceTemp = paymentPriceTemp.add(skuAmount);
            // 订单累计优惠金额
            discountPriceTemp = discountPriceTemp.add(skuDiscountAmount);
        }

        return shopPurchasesVo;
    }

}
