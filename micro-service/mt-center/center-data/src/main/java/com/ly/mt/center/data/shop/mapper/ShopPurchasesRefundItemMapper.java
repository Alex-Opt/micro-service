package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopPurchasesRefundItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopPurchasesRefundItemMapper {
    /**
     * @Description 保存ShopPurchasesRefundItem
     * @Author taoye
     */
    void insertShopPurchasesRefundItem(ShopPurchasesRefundItem shopPurchasesRefundItem);

    /**
     * @Description 删除ShopPurchasesRefundItem
     * @Author taoye
     */
    void deleteShopPurchasesRefundItem(ShopPurchasesRefundItem shopPurchasesRefundItem);

    /**
     * @Description 更新ShopPurchasesRefundItem
     * @Author taoye
     */
    int updateShopPurchasesRefundItem(ShopPurchasesRefundItem shopPurchasesRefundItem);

    /**
     * @Description 查询ShopPurchasesRefundItem
     * @Author taoye
     */
    ShopPurchasesRefundItem getShopPurchasesRefundItem(ShopPurchasesRefundItem shopPurchasesRefundItem);

    /**
     * @Description 查询商家累计退货数
     * @Author linan
     */
    String getRefundNum(ShopPurchasesRefundItem shopPurchasesRefundItem);
}