package com.ly.mt.core.base.entity.shop;

import com.ly.mt.core.base.entity.purchase.ShopPurchasesDiscount;

import java.util.List;

/**
 * 进货单查询的返回页面的模型
 * @author zhanglifeng
 * @date 2019-06-21
 *//** @deprecated */
public class ShopPurchaseVo {

    /**
     * 进货单信息
     */
   private ShopPurchases shopPurchases;

    /**
     * 统计购买的商品数量
     */
   private Integer skuNumber;

    /**
     * 针对一个进货单主表的进货单明细信息
     */
   private List<ShopPurchasesItemVo> shopPurchasesItemsList;

    /**
     * 进货优惠信息集合
     */
   private List<ShopPurchasesDiscount> discountList;


    public ShopPurchases getShopPurchases() {
        return shopPurchases;
    }

    public void setShopPurchases(ShopPurchases shopPurchases) {
        this.shopPurchases = shopPurchases;
    }

    public List<ShopPurchasesItemVo> getShopPurchasesItemsList() {
        return shopPurchasesItemsList;
    }

    public void setShopPurchasesItemsList(List<ShopPurchasesItemVo> shopPurchasesItemsList) {
        this.shopPurchasesItemsList = shopPurchasesItemsList;
    }

    public List<ShopPurchasesDiscount> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<ShopPurchasesDiscount> discountList) {
        this.discountList = discountList;
    }

    public Integer getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(Integer skuNumber) {
        this.skuNumber = skuNumber;
    }
}
