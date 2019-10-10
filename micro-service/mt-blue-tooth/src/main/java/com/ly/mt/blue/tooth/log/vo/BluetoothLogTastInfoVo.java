package com.ly.mt.blue.tooth.log.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 蓝牙APP-首页烟弹统计数据
 */
@ApiModel(value="蓝牙首页烟弹信息")
public class BluetoothLogTastInfoVo {
    @ApiModelProperty(value = "烟弹名称", required = true)
    private String taste;
    @ApiModelProperty(value = "烟弹key", required = true)
    private String tasteKey;
    @ApiModelProperty(value = "图标url", required = true)
    private String iconUrl;
    @ApiModelProperty(value = "添加时间", required = true)
    private String addTime;
    @ApiModelProperty(value = "烟弹剩余 示例：89", required = true)
    private int last;//烟弹剩余
    @ApiModelProperty(value = "烟杆电量", required = true)
    private int electricQuantity;//电量
    @ApiModelProperty(value = "日均抽烟口数", required = true)
    private int avgSmoking;//烟弹剩余
    //    @ApiModelProperty(value = "烟弹可抽可数 废弃", required = true)
//    private int numberOfMouths;//可抽可数
    @ApiModelProperty(value = "烟杆累计抽的口数 示例：100", required = true)
    private int tobaccoTotalSmokingMouth;//烟杆累计抽的口数

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getTasteKey() {
        return tasteKey;
    }

    public void setTasteKey(String tasteKey) {
        this.tasteKey = tasteKey;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getTobaccoTotalSmokingMouth() {
        return tobaccoTotalSmokingMouth;
    }

    public void setTobaccoTotalSmokingMouth(int tobaccoTotalSmokingMouth) {
        this.tobaccoTotalSmokingMouth = tobaccoTotalSmokingMouth;
    }

    public int getAvgSmoking() {
        return avgSmoking;
    }

    public void setAvgSmoking(int avgSmoking) {
        this.avgSmoking = avgSmoking;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getElectricQuantity() {
        return electricQuantity;
    }

    public void setElectricQuantity(int electricQuantity) {
        this.electricQuantity = electricQuantity;
    }
}