package com.ly.mt.home.tob.purchases.factory.decorator;

import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.home.base.constant.ShopConstant;
import com.ly.mt.home.tob.discount.vo.ShopPurchasesDiscountVo;
import com.ly.mt.home.tob.purchases.factory.calc.MultiplyCalc;
import com.ly.mt.home.tob.purchases.factory.calc.SubtractCalc;
import com.ly.mt.home.tob.purchases.vo.ShopPurchasesVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author: linan
 * @date: 2019/09/11
 **/
public abstract class AbstractPriceComponent {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected BigDecimal percentages;

    protected BigDecimal minuend;

    protected String discountType;

    protected String remarks;

    MultiplyCalc multiplyCalc;

    SubtractCalc subtractCalc;

    public AbstractPriceComponent() {
        multiplyCalc = new MultiplyCalc();
        subtractCalc = new SubtractCalc();
    }

    /**
     * 折扣算法
     *
     * @param shopPurchasesVo
     * @return
     */
    protected ShopPurchasesVo multiply(ShopPurchasesVo shopPurchasesVo) {
        return discountDataBusiness(multiplyCalc.calc(shopPurchasesVo, percentages));
    }

    /**
     * 满减算法
     *
     * @param shopPurchasesVo
     * @return
     */
    protected ShopPurchasesVo subtract(ShopPurchasesVo shopPurchasesVo) {
        return discountDataBusiness(subtractCalc.calc(shopPurchasesVo, minuend));
    }

    /**
     * 优惠记录
     *
     * @param shopPurchasesVo
     * @return
     */
    private ShopPurchasesVo discountDataBusiness(ShopPurchasesVo shopPurchasesVo) {
        List<ShopPurchasesDiscountVo> discountVoList = shopPurchasesVo.getDiscountList();
        discountVoList.add(
                new ShopPurchasesDiscountVo.Builder()
                        .shopId(shopPurchasesVo.getShopId())
                        .userId(shopPurchasesVo.getUserId())
                        .purchasesId(shopPurchasesVo.getId())
                        .discountType(discountType)
                        .status(ShopConstant.DiscountStatus.ABNORMAL.getValue())
                        .discountRate(String.valueOf(percentages == null ? 0 : percentages))
                        .discountPrice((String.valueOf(minuend == null ? 0 : minuend)) )
                        .createTime(DateUtil.getNowTimeStr())
                        .remarks(remarks)
                        .build());
        shopPurchasesVo.setDiscountList(discountVoList);
        return shopPurchasesVo;
    }

    /**
     * 业务计算
     *
     * @param shopPurchasesVo
     * @return
     */
    public abstract ShopPurchasesVo calc(ShopPurchasesVo shopPurchasesVo);
}
