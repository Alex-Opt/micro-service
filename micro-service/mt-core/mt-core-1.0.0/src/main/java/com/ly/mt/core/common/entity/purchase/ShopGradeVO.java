package com.ly.mt.core.common.entity.purchase;

import java.io.Serializable;

/**
 * 店铺会员等级配置
 * @author xiaobei
 * @date 2019-06-21 08:04:04
 */
public class ShopGradeVO implements Serializable {

    private static final long serialVersionUID = -1526639025363456837L;

    private String id;

    private String title;

    private String purchasesMoney;

    private String purchasesNum;

    private String promotionMoney;

    private String promotionRate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPurchasesMoney() {
        return purchasesMoney;
    }

    public void setPurchasesMoney(String purchasesMoney) {
        this.purchasesMoney = purchasesMoney;
    }

    public String getPurchasesNum() {
        return purchasesNum;
    }

    public void setPurchasesNum(String purchasesNum) {
        this.purchasesNum = purchasesNum;
    }

    public String getPromotionMoney() {
        return promotionMoney;
    }

    public void setPromotionMoney(String promotionMoney) {
        this.promotionMoney = promotionMoney;
    }

    public String getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(String promotionRate) {
        this.promotionRate = promotionRate;
    }

    @Override
    public String toString() {
        return "ShopGradeVO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", purchasesMoney='" + purchasesMoney + '\'' +
                ", purchasesNum='" + purchasesNum + '\'' +
                ", promotionMoney='" + promotionMoney + '\'' +
                ", promotionRate='" + promotionRate + '\'' +
                '}';
    }
}
