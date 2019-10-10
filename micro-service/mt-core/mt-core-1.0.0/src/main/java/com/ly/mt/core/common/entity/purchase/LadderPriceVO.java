package com.ly.mt.core.common.entity.purchase;

import java.io.Serializable;

/**
 * 阶梯价优惠信息
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-25 23:21:21
 */
public class LadderPriceVO implements Serializable {

    private static final long serialVersionUID = 2736912330571122158L;

    /**
     * 当前折扣率需进货的最小数量
     */
    private String purachasesNum;

    /**
     * 用户累计已进货的数量
     */
    private String purchasedNum;

    /**
     * 本次进货如要满足该折扣率需要进货的最小数量
     */
    private String thisNum;

    /**
     * 当前的折扣率
     */
    private String thisPromotionRate;

    public String getPurachasesNum() {
        return purachasesNum;
    }

    public void setPurachasesNum(String purachasesNum) {
        this.purachasesNum = purachasesNum;
    }

    public String getPurchasedNum() {
        return purchasedNum;
    }

    public void setPurchasedNum(String purchasedNum) {
        this.purchasedNum = purchasedNum;
    }

    public String getThisNum() {
        return thisNum;
    }

    public void setThisNum(String thisNum) {
        this.thisNum = thisNum;
    }

    public String getThisPromotionRate() {
        return thisPromotionRate;
    }

    public void setThisPromotionRate(String thisPromotionRate) {
        this.thisPromotionRate = thisPromotionRate;
    }

    @Override
    public String toString() {
        return "LadderPriceVO{" +
                "purachasesNum='" + purachasesNum + '\'' +
                ", purchasedNum='" + purchasedNum + '\'' +
                ", thisNum='" + thisNum + '\'' +
                ", thisPromotionRate='" + thisPromotionRate + '\'' +
                '}';
    }
}
