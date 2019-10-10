package com.ly.mt.blue.tooth.log.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value="蓝牙统计信息")
public class BluetoothLogCountInfoVo {
    @ApiModelProperty(value = "图表信息集合", required = true)
    List<BluetoothLogChartVo> chartList = new ArrayList<>();//图表数据
    @ApiModelProperty(value = "有害物质少吸入量", required = true)
    private int lessHarmful ;//有害物质少吸入量
    @ApiModelProperty(value = "累计少抽香烟根数", required = true)
    private int lessCigarettes;//累计少抽香烟根数
    @ApiModelProperty(value = "抽烟次数", required = true)
    private int smokingTimes;//抽烟次数
    @ApiModelProperty(value = "抽烟口数", required = true)
    private int smokingNumber;//抽烟口数
    @ApiModelProperty(value = "总口数或日均抽烟数", required = true)
    private int totalOrAvgDayNumber;//总口数或日均抽烟数

    public List<BluetoothLogChartVo> getChartList() {
        return chartList;
    }

    public void setChartList(List<BluetoothLogChartVo> chartList) {
        this.chartList = chartList;
    }

    public int getLessHarmful() {
        return lessHarmful;
    }

    public void setLessHarmful(int lessHarmful) {
        this.lessHarmful = lessHarmful;
    }

    public int getLessCigarettes() {
        return lessCigarettes;
    }

    public void setLessCigarettes(int lessCigarettes) {
        this.lessCigarettes = lessCigarettes;
    }

    public int getSmokingTimes() {
        return smokingTimes;
    }

    public void setSmokingTimes(int smokingTimes) {
        this.smokingTimes = smokingTimes;
    }

    public int getSmokingNumber() {
        return smokingNumber;
    }

    public void setSmokingNumber(int smokingNumber) {
        this.smokingNumber = smokingNumber;
    }

    public int getTotalOrAvgDayNumber() {
        return totalOrAvgDayNumber;
    }

    public void setTotalOrAvgDayNumber(int totalOrAvgDayNumber) {
        this.totalOrAvgDayNumber = totalOrAvgDayNumber;
    }
}