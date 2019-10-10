package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopExclusiveDiscount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 商家专属优惠
 * @author: linan
 * @date: 2019/9/6
 */
@Mapper
public interface ShopExclusiveDiscountMapper {

    ShopExclusiveDiscount getShopExclusiveDiscount(ShopExclusiveDiscount shopExclusiveDiscount);
}