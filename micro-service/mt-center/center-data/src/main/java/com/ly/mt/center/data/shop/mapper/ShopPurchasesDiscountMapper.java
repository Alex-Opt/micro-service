package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopPurchasesDiscount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopPurchasesDiscountMapper {
    /**
     * @Description 保存ShopPurchasesDiscount
     * @Author taoye
     */
    void insertShopPurchasesDiscount(ShopPurchasesDiscount shopPurchasesDiscount);

    /**
     * @Description 删除ShopPurchasesDiscount
     * @Author taoye
     */
    void deleteShopPurchasesDiscount(ShopPurchasesDiscount shopPurchasesDiscount);

    /**
     * @Description 更新ShopPurchasesDiscount
     * @Author taoye
     */
    int updateShopPurchasesDiscount(ShopPurchasesDiscount shopPurchasesDiscount);

    /**
     * @Description 查询ShopPurchasesDiscount
     * @Author taoye
     */
    ShopPurchasesDiscount getShopPurchasesDiscount(ShopPurchasesDiscount shopPurchasesDiscount);

    /**
     * 查询ShopPurchasesDiscount
     *
     * @param shopPurchasesDiscount
     * @return
     */
    String getShopTotalDiscount(ShopPurchasesDiscount shopPurchasesDiscount);

    /**
     *
     * @param shopPurchasesDiscount
     * @return
     */
    List<ShopPurchasesDiscount> getShopPurchasesDiscountList(ShopPurchasesDiscount shopPurchasesDiscount);
}