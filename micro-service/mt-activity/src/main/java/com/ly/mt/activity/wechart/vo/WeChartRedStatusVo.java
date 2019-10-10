package com.ly.mt.activity.wechart.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author taoye
 */
@ApiModel(value = "code=0时返回")
public class WeChartRedStatusVo {
    @ApiModelProperty(
            value = "红包发放状态：  \n" +
                    "SENDING————发放中  \n" +
                    "SENT———————已发放待领取  \n" +
                    "FAILED—————发放失败  \n" +
                    "RECEIVED———已领取  \n" +
                    "RFUND_ING——退款中  \n" +
                    "REFUND—————已退款"
    )
    private String status;
    @ApiModelProperty("红包金额(分)")
    private String totalAmount;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}