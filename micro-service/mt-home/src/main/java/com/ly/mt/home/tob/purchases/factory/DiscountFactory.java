package com.ly.mt.home.tob.purchases.factory;

import com.ly.mt.home.tob.purchases.factory.decorator.*;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

/**
 * @author: linan
 * @date: 2019/09/17
 **/
public class DiscountFactory {

    public static final String TYPE_LADDER = "1";
    public static final String TYPE_EXCLUSIVE = "2";
    public static final String TYPE_COUPON = "3";

    /**
     * 构造装饰器，如果类型为空的话，则默认不适用优惠装饰，只使用基本装饰
     *
     * @param type
     * @return
     */
    public static AbstractPriceComponent createDecorator(String type){
        return createDecorator(type, new BasicDecorator());
    }

    /**
     * 构造装饰器，用于适用多种优惠策略的订单
     *
     * @param types
     * @return
     */
    public static AbstractPriceComponent createDecorator(String... types){
        if(StringUtils.isEmpty(Arrays.toString(types))) {
            return null;
        }

        types = sort(types);
        AbstractPriceComponent abstractDiscount = null;
        for (String type : types) {
            if(abstractDiscount == null){
                abstractDiscount = createDecorator(type);
            } else {
                abstractDiscount = createDecorator(type, abstractDiscount);
            }

        }
        return abstractDiscount;
    }

    private static AbstractPriceComponent createDecorator(String type, AbstractPriceComponent abstractDiscount){
        if(StringUtils.isEmpty(type)){
            return null;
        }

        switch (type) {
            case TYPE_LADDER :
                return new LadderDecorator(abstractDiscount);
            case TYPE_EXCLUSIVE :
                return new ExclusiveDecorator(abstractDiscount);
            case TYPE_COUPON :
                return new CouponDecorator(abstractDiscount);
            default:
                return abstractDiscount;
        }
    }

    /**
     * 策略排序, 防止策略混乱导致装饰异常
     * todo： code/vertify
     *
     * @param types
     * @return
     */
    private static String[] sort(String... types){
        return types;
    }
}
