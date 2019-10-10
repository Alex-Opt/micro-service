package com.ly.mt.blue.tooth.log.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @program: mt-blue-tooth
* @description: 抽烟日数据分享Vo
* @author: wanghongliang
* @create: 2019/7/26 11:11
**/
@ApiModel(value="用户抽烟日数据分享Vo",description="以英文pub开头是所有分享页面公用字段\n" +
        "day开头是分享类型为日页面所需字段\n")
public class BluetoothSmokingDayDataShareVo {
    @ApiModelProperty(value="烟杆添加日期")
    private String pubDate;
    @ApiModelProperty(value="烟杆到当前日期天数")
    private int dayTotal;
    @ApiModelProperty(value="累计吸烟口数")
    private int dayTotalSmoingMouthNumber;
    @ApiModelProperty(value="累计节省多少钱")
    private int dayTotalSaveMoney;
    @ApiModelProperty(value="累计少吸香烟根数")
    private int dayTotalLessCigarettes;
    @ApiModelProperty(value="健康加时间")
    private int daycontinueOneSLifeMin;
    @ApiModelProperty(value="累计少吸有害物质")
    private int dayLessHarmful;

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public int getDayTotal() {
        return dayTotal;
    }

    public void setDayTotal(int dayTotal) {
        this.dayTotal = dayTotal;
    }

    public int getDayTotalSmoingMouthNumber() {
        return dayTotalSmoingMouthNumber;
    }

    public void setDayTotalSmoingMouthNumber(int dayTotalSmoingMouthNumber) {
        this.dayTotalSmoingMouthNumber = dayTotalSmoingMouthNumber;
    }

    public int getDayTotalSaveMoney() {
        return dayTotalSaveMoney;
    }

    public void setDayTotalSaveMoney(int dayTotalSaveMoney) {
        this.dayTotalSaveMoney = dayTotalSaveMoney;
    }

    public int getDayTotalLessCigarettes() {
        return dayTotalLessCigarettes;
    }

    public void setDayTotalLessCigarettes(int dayTotalLessCigarettes) {
        this.dayTotalLessCigarettes = dayTotalLessCigarettes;
    }

    public int getDaycontinueOneSLifeMin() {
        return daycontinueOneSLifeMin;
    }

    public void setDaycontinueOneSLifeMin(int daycontinueOneSLifeMin) {
        this.daycontinueOneSLifeMin = daycontinueOneSLifeMin;
    }

    public int getDayLessHarmful() {
        return dayLessHarmful;
    }

    public void setDayLessHarmful(int dayLessHarmful) {
        this.dayLessHarmful = dayLessHarmful;
    }
}