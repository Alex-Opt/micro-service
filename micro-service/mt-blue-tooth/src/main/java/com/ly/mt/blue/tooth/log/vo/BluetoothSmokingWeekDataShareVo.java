package com.ly.mt.blue.tooth.log.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @program: mt-blue-tooth
* @description: 抽烟周数据分享Vo
* @author: wanghongliang
* @create: 2019/7/26 11:11
**/
@ApiModel(value="用户抽烟周数据分享Vo",description="以英文week开头是周页面所需字段")

public class BluetoothSmokingWeekDataShareVo {
    @ApiModelProperty(value="健康指数")
    private int weekHealthIndex;
    @ApiModelProperty(value="本周少吸入有害物质-/对比上周增加/减少-正数增加/负数减少")
    private int weekContrastLastLessHarmful;
    @ApiModelProperty(value="累计少吸入有害物质-计算累计")
    private int weekTotalLessHarmful;
    @ApiModelProperty(value="本周少吸烟根数-对比上周增加/减少-正数增加/负数减少")
    private int weekContrastLastLessCigarettes;
    @ApiModelProperty(value="累计少吸烟多少根")
    private int weekTotalLessCigarettes;
    @ApiModelProperty(value="本周吸MOTI口数-对比上周增加/减少正数增加/负数减少")
    private int weekContrastSmoingMouthNumber;
    @ApiModelProperty(value="累计少吸烟多少口-计算累计")
    private int weekTotalSmoingMouthNumber;

    public int getWeekHealthIndex() {
        return weekHealthIndex;
    }

    public void setWeekHealthIndex(int weekHealthIndex) {
        this.weekHealthIndex = weekHealthIndex;
    }

    public int getWeekContrastLastLessHarmful() {
        return weekContrastLastLessHarmful;
    }

    public void setWeekContrastLastLessHarmful(int weekContrastLastLessHarmful) {
        this.weekContrastLastLessHarmful = weekContrastLastLessHarmful;
    }

    public int getWeekTotalLessHarmful() {
        return weekTotalLessHarmful;
    }

    public void setWeekTotalLessHarmful(int weekTotalLessHarmful) {
        this.weekTotalLessHarmful = weekTotalLessHarmful;
    }

    public int getWeekContrastLastLessCigarettes() {
        return weekContrastLastLessCigarettes;
    }

    public void setWeekContrastLastLessCigarettes(int weekContrastLastLessCigarettes) {
        this.weekContrastLastLessCigarettes = weekContrastLastLessCigarettes;
    }

    public int getWeekTotalLessCigarettes() {
        return weekTotalLessCigarettes;
    }

    public void setWeekTotalLessCigarettes(int weekTotalLessCigarettes) {
        this.weekTotalLessCigarettes = weekTotalLessCigarettes;
    }

    public int getWeekContrastSmoingMouthNumber() {
        return weekContrastSmoingMouthNumber;
    }

    public void setWeekContrastSmoingMouthNumber(int weekContrastSmoingMouthNumber) {
        this.weekContrastSmoingMouthNumber = weekContrastSmoingMouthNumber;
    }

    public int getWeekTotalSmoingMouthNumber() {
        return weekTotalSmoingMouthNumber;
    }

    public void setWeekTotalSmoingMouthNumber(int weekTotalSmoingMouthNumber) {
        this.weekTotalSmoingMouthNumber = weekTotalSmoingMouthNumber;
    }
}