package com.ly.mt.activity.wechart.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author taoye
 */
@ApiModel(value = "code=0时返回,code=1时可能为null")
public class WeChartSendRedVo {
    @ApiModelProperty(value = "订单号")
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}