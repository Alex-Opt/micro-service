package com.ly.mt.home.tob.purchases.factory.decorator;

import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.home.base.constant.ShopConstant;
import com.ly.mt.home.base.exception.MTException;
import com.ly.mt.home.base.util.ApplicationContextHelper;
import com.ly.mt.home.tob.coupon.service.ShopCouponService;
import com.ly.mt.home.tob.coupon.vo.CouponInfoVo;
import com.ly.mt.home.tob.purchases.factory.DiscountFactory;
import com.ly.mt.home.tob.purchases.vo.ShopPurchasesVo;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: linan
 * @date: 2019/09/11
 **/
public class CouponDecorator extends AbstractPriceComponent {

    private ShopCouponService shopCouponService = ApplicationContextHelper.getBean(ShopCouponService.class);

    private AbstractPriceComponent abstractDiscount;

    public CouponDecorator(AbstractPriceComponent abstractDiscount) {
        this.discountType = DiscountFactory.TYPE_COUPON;
        this.abstractDiscount = abstractDiscount;
    }

    @Override
    public ShopPurchasesVo calc(ShopPurchasesVo shopPurchasesVo) {
        if(abstractDiscount != null) {
            abstractDiscount.calc(shopPurchasesVo);
        }

        CouponInfoVo couponInfoVo = shopCouponService.getCoupon(shopPurchasesVo.getCouponId());

        Assert.notNull(couponInfoVo, "优惠券不存在");
        Assert.isTrue(couponInfoVo.getUseStatus().equals(ShopConstant.ShopCouponStatus.NO_USE.getValue()), "优惠券已使用");

        boolean flag = false;
        try {
            flag = (DateUtil.compareDateTime(couponInfoVo.getInvalidTime(), DateUtil.date2TimeStr(new Date())) >= 0);
        } catch (Exception e) {
            throw new MTException("优惠券异常");
        }
        Assert.isTrue(flag, "优惠券已过期");



        if(StringUtil.isNotEmpty(couponInfoVo.getDiscountRate())) {
            percentages = new BigDecimal(couponInfoVo.getDiscountRate());
            remarks = String.valueOf(percentages.multiply(new BigDecimal(10)).stripTrailingZeros().toPlainString()).concat("折");

            shopPurchasesVo = this.multiply(shopPurchasesVo);
        } else if(StringUtil.isNotEmpty(couponInfoVo.getDenomination())) {
            if(new BigDecimal(shopPurchasesVo.getActualAmount()).compareTo(
                    new BigDecimal(couponInfoVo.getStartFee())) >= 0) {
                minuend = new BigDecimal(couponInfoVo.getDenomination());
                remarks = "减".concat(String.valueOf(minuend.stripTrailingZeros().toPlainString())).concat("元");

                shopPurchasesVo = this.multiply(shopPurchasesVo);
            } else {
                throw  new MTException("优惠券不满足使用条件");
            }
        }
        return shopPurchasesVo;
    }

}
