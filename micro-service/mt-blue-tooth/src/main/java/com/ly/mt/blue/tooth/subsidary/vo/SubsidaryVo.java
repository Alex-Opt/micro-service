package com.ly.mt.blue.tooth.subsidary.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description user
 * @Author whl
 */
@ApiModel(value="目标设定")
public class SubsidaryVo {
    /**
     * @Description 戒烟目标(每天不超过)
     */
    @ApiModelProperty(value = "戒烟目标(每天不超过)", required = true)
    private int smokingTarget;
    /**
     * @Description 达标天数(健康抽烟)
     */
    @ApiModelProperty(value = "达标天数(健康抽烟)", required = true)
    private int complianceDaysTarget;

    public int getSmokingTarget() {
        return smokingTarget;
    }

    public void setSmokingTarget(int smokingTarget) {
        this.smokingTarget = smokingTarget;
    }

    public int getComplianceDaysTarget() {
        return complianceDaysTarget;
    }

    public void setComplianceDaysTarget(int complianceDaysTarget) {
        this.complianceDaysTarget = complianceDaysTarget;
    }
}