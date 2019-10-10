package com.ly.mt.core.base.entity.hd.vo;


/**
 * @description
 * 活动门店商品dto
 * @author panjingtian
 * @date 2019/6/19 4:31 PM
 *//** @deprecated */
public class HdShopInfoDto {

    private Long id;

    private String shopName;
    private Long operatorId;

    public HdShopInfoDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
}
