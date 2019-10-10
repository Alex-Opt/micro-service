package com.ly.mt.blue.tooth.mq.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 蓝牙烟杆传输数据对象
 */
@ApiModel(value="蓝牙烟杆抽烟数据")
public class BluetoothLogVo {
    @ApiModelProperty(value = "时间戳", required = true)
    private String timeStamp;//时间戳
    @ApiModelProperty(value = "功率", required = true)
    private String power;//功率
    @ApiModelProperty(value = "持续时间", required = true)
    private String time;//持续时间

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}