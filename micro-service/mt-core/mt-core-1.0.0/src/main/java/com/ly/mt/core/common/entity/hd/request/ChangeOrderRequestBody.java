package com.ly.mt.core.common.entity.hd.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 修改订单请求体
 */
@ApiModel("修改订单请求体")
public class ChangeOrderRequestBody {

    @ApiModelProperty("订单状态")
    private String orderStatus;

    @ApiModelProperty("取货码")
    private String getProductCode;

    @ApiModelProperty("单号")
    private String orderId;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getGetProductCode() {
        return getProductCode;
    }

    public void setGetProductCode(String getProductCode) {
        this.getProductCode = getProductCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
