package com.ly.mt.core.common.entity.purchase;

import java.io.Serializable;

/**
 * B端会员信息
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-16 21:28:28
 */
public class MemberInfoVO implements Serializable {

    private static final long serialVersionUID = -4678789917408235945L;
    /**
     * 进货数
     */
    private String stockNum;

    /**
     * 等级名称
     */
    private String title;

    /**
     * 等级图标
     */
    private String icon;

    /**
     * 优惠折扣
     */
    private String promotionRate;

    public String getStockNum() {
        return stockNum;
    }

    public void setStockNum(String stockNum) {
        this.stockNum = stockNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(String promotionRate) {
        this.promotionRate = promotionRate;
    }

    @Override
    public String toString() {
        return "MemberInfoVO{" +
                "stockNum='" + stockNum + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", promotionRate='" + promotionRate + '\'' +
                '}';
    }
}
