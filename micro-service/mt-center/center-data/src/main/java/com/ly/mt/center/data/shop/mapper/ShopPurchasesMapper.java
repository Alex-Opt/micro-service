package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopPurchases;
import com.ly.mt.center.data.shop.entity.ShopPurchasesModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopPurchasesMapper {
    /**
     * @Description 保存ShopPurchases
     * @Author taoye
     */
    void insertShopPurchases(ShopPurchases shopPurchases);

    /**
     * @Description 删除ShopPurchases
     * @Author taoye
     */
    void deleteShopPurchases(ShopPurchases shopPurchases);

    /**
     * @Description 更新ShopPurchases
     * @Author taoye
     */
    int updateShopPurchases(ShopPurchases shopPurchases);

    /**
     * @Description 查询ShopPurchases
     * @Author taoye
     */
    ShopPurchasesModel getShopPurchases(ShopPurchases shopPurchases);

    /**
     * @Description 查询ShopPurchasesList
     * @Author taoye
     */
    List<ShopPurchases> getShopPurchasesList(ShopPurchases shopPurchases);
}