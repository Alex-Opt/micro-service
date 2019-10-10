package com.ly.mt.home.tob.ladder.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class LadderVo implements Comparable<LadderVo>, Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "优惠件数", required = true)
    private String purachasesNum;
    @ApiModelProperty(value = "优惠折扣", required = true)
    private String promotionRate;

    public LadderVo() {
    }

    public String getPurachasesNum() {
        return purachasesNum;
    }

    public void setPurachasesNum(String purachasesNum) {
        this.purachasesNum = purachasesNum;
    }

    public String getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(String promotionRate) {
        this.promotionRate = promotionRate;
    }


    @Override
    public int compareTo(LadderVo o) {
        return Integer.parseInt(o.getPurachasesNum()) - Integer.parseInt(this.purachasesNum);
    }

    @Override
    public String toString() {
        return "LadderVo{" +
                "purachasesNum='" + purachasesNum + '\'' +
                ", promotionRate='" + promotionRate + '\'' +
                '}';
    }
}


