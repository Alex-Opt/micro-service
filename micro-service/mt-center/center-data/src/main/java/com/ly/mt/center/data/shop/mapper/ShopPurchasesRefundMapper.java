package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopPurchasesRefund;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopPurchasesRefundMapper {
    /**
     * @Description 保存ShopPurchasesRefund
     * @Author taoye
     */
    void insertShopPurchasesRefund(ShopPurchasesRefund shopPurchasesRefund);

    /**
     * @Description 删除ShopPurchasesRefund
     * @Author taoye
     */
    void deleteShopPurchasesRefund(ShopPurchasesRefund shopPurchasesRefund);

    /**
     * @Description 更新ShopPurchasesRefund
     * @Author taoye
     */
    int updateShopPurchasesRefund(ShopPurchasesRefund shopPurchasesRefund);

    /**
     * @Description 查询ShopPurchasesRefund
     * @Author taoye
     */
    ShopPurchasesRefund getShopPurchasesRefund(ShopPurchasesRefund shopPurchasesRefund);
}