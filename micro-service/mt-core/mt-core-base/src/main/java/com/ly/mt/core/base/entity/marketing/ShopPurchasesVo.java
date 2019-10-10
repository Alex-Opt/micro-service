package com.ly.mt.core.base.entity.marketing;

/**
 *@Description 商家进货对象
 *@Author  zhuyh
 *//** @deprecated */
public class ShopPurchasesVo {

    /**
     * 商家id
     */
    private Long shopId;

    /**
     * 进货数量
     */
    private Integer cou;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getCou() {
        return cou;
    }

    public void setCou(Integer cou) {
        this.cou = cou;
    }
}
