package com.ly.mt.core.common.entity.trade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 484876123@qq.com
 */
@ApiModel( value = "创建退货/退款单" ,description = "创建退货/退款单")
public class AddTradeOrderRefundDto {
    @ApiModelProperty(value = "订单号" ,name="orderId" ,required=true)
    private String orderId;
    @ApiModelProperty(value = "买家id" ,name="buyerId" ,required=true)
    private String buyerId;
    @ApiModelProperty(value = "退款描述" ,name="refundReason" ,required=true)
    private String refundReason;
    @ApiModelProperty(value = "退款原因" ,name="refundDesc")
    private String refundDesc;
    @ApiModelProperty(value = "退货单品sku码" ,name="skuId")
    private String skuId;
    @ApiModelProperty(value = "退货数量" ,name="refundsCount")
    private String refundsCount;

    public String getRefundsCount() {
        return refundsCount;
    }

    public void setRefundsCount(String refundsCount) {
        this.refundsCount = refundsCount;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
    public String getBuyerId() {
        return buyerId;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }
    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc;
    }
    public String getRefundDesc() {
        return refundDesc;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
    public String getSkuId() {
        return skuId;
    }
}
