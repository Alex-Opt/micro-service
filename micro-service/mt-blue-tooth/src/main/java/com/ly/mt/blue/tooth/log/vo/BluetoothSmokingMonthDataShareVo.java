package com.ly.mt.blue.tooth.log.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @program: mt-blue-tooth
* @description: 抽烟月数据分享Vo
* @author: wanghongliang
* @create: 2019/7/26 11:11
**/
@ApiModel(value="用户抽烟月数据分享Vo",description="以英文month开头为月字段\n")

public class BluetoothSmokingMonthDataShareVo {
    @ApiModelProperty(value="健康指数")
    private int monthHealthIndex;
    @ApiModelProperty(value="本月少吸入有害物质-/对比上月增加/减少-正数增加/负数减少")
    private int monthContrastLastLessHarmful;
    @ApiModelProperty(value="累计少吸入有害物质-计算累计")
    private int monthTotalLessHarmful;
    @ApiModelProperty(value="本月少吸烟根数-对比上月增加/减少-正数增加/负数减少")
    private int monthContrastLastLessCigarettes;
    @ApiModelProperty(value="累计少吸烟多少根")
    private int monthTotalLessCigarettes;
    @ApiModelProperty(value="本月吸MOTI口数-对比上月增加/减少正数增加/负数减少")
    private int monthContrastSmoingMouthNumber;
    @ApiModelProperty(value="累计少吸烟多少口-计算累计")
    private int monthTotalSmoingMouthNumber;

    public int getMonthHealthIndex() {
        return monthHealthIndex;
    }

    public void setMonthHealthIndex(int monthHealthIndex) {
        this.monthHealthIndex = monthHealthIndex;
    }

    public int getMonthContrastLastLessHarmful() {
        return monthContrastLastLessHarmful;
    }

    public void setMonthContrastLastLessHarmful(int monthContrastLastLessHarmful) {
        this.monthContrastLastLessHarmful = monthContrastLastLessHarmful;
    }

    public int getMonthTotalLessHarmful() {
        return monthTotalLessHarmful;
    }

    public void setMonthTotalLessHarmful(int monthTotalLessHarmful) {
        this.monthTotalLessHarmful = monthTotalLessHarmful;
    }

    public int getMonthContrastLastLessCigarettes() {
        return monthContrastLastLessCigarettes;
    }

    public void setMonthContrastLastLessCigarettes(int monthContrastLastLessCigarettes) {
        this.monthContrastLastLessCigarettes = monthContrastLastLessCigarettes;
    }

    public int getMonthTotalLessCigarettes() {
        return monthTotalLessCigarettes;
    }

    public void setMonthTotalLessCigarettes(int monthTotalLessCigarettes) {
        this.monthTotalLessCigarettes = monthTotalLessCigarettes;
    }

    public int getMonthContrastSmoingMouthNumber() {
        return monthContrastSmoingMouthNumber;
    }

    public void setMonthContrastSmoingMouthNumber(int monthContrastSmoingMouthNumber) {
        this.monthContrastSmoingMouthNumber = monthContrastSmoingMouthNumber;
    }

    public int getMonthTotalSmoingMouthNumber() {
        return monthTotalSmoingMouthNumber;
    }

    public void setMonthTotalSmoingMouthNumber(int monthTotalSmoingMouthNumber) {
        this.monthTotalSmoingMouthNumber = monthTotalSmoingMouthNumber;
    }
}