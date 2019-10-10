package com.ly.mt.blue.tooth.tobarod.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 蓝牙APP-烟杆电量详情
 */
@ApiModel(value="烟杆电量详情")
public class BluetoothElectricQuantityDetailVo {
    @ApiModelProperty(value = "可抽天数", required = true)
    private int onStandby;//可抽天数
    @ApiModelProperty(value = "可抽口数", required = true)
    private int smokingMouthNumber;//可抽口数
    @ApiModelProperty(value = "电量充满时间", required = true)
    private int fullOfTime;//电量充满时间
    @ApiModelProperty(value = "电量", required = true)
    private int electricQuantity;//电量

    public int getOnStandby() {
        return onStandby;
    }

    public void setOnStandby(int onStandby) {
        this.onStandby = onStandby;
    }

    public int getSmokingMouthNumber() {
        return smokingMouthNumber;
    }

    public void setSmokingMouthNumber(int smokingMouthNumber) {
        this.smokingMouthNumber = smokingMouthNumber;
    }

    public int getFullOfTime() {
        return fullOfTime;
    }

    public void setFullOfTime(int fullOfTime) {
        this.fullOfTime = fullOfTime;
    }

    public int getElectricQuantity() {
        return electricQuantity;
    }

    public void setElectricQuantity(int electricQuantity) {
        this.electricQuantity = electricQuantity;
    }
}