package com.ly.mt.core.data.shop.mapper;

import com.ly.mt.core.data.shop.entity.ShopPurchasesDiscount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ShopPurchasesDiscount操作接口
 *
 * @author taoye
 */
@Mapper
public interface ShopPurchasesDiscountMapper {
    /**
     * 查询List<ShopPurchasesDiscount>
     *
     * @param shopPurchasesDiscount 查询条件
     * @return List<ShopPurchasesDiscount>
     * @author taoye
     */
    List<ShopPurchasesDiscount> listShopPurchasesDiscount(ShopPurchasesDiscount shopPurchasesDiscount);
}