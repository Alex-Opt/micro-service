package com.ly.mt.blue.tooth.log.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 蓝牙统计图表对象
 */
@ApiModel(value="蓝牙图表信息")
public class BluetoothLogChartVo {
    @ApiModelProperty(value = "x轴 时间", required = true)
    private String x;//x轴 时间
    @ApiModelProperty(value = "y轴 口数", required = true)
    private int y;//y轴 口数
    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}