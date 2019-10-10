package com.ly.mt.core.base.entity.hd.dto;

/**
 * 带活动门店注册信息id的dto
 *//** @deprecated */
public class AttShopInfoDto extends ShopInfoDto {

    /**
     * 门店活动信息主键id
     */
    private Long shopAttDetailId;

    public Long getShopAttDetailId() {
        return shopAttDetailId;
    }

    public void setShopAttDetailId(Long shopAttDetailId) {
        this.shopAttDetailId = shopAttDetailId;
    }
}
