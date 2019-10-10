package com.ly.mt.cabinet.c.alipay.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AliPayReqVO {
    @ApiModelProperty(value = "订单编号", name = "orderNo", example = "11223344")
    private long orderNo;
    @ApiModelProperty(value = "订单金额", name = "amount", example = "199.00")
    private Double amount;
    @ApiModelProperty(value = "订单描述", name = "subject", example = "0")
    private String subject;
    @ApiModelProperty(value = "格子柜编号", name = "gzgCode", example = "11223344")
    private String gzgCode;


    @Override
    public String toString() {
        return "AliPayReqVO{" +
                "orderNo=" + orderNo +
                ", amount=" + amount +
                ", subject='" + subject + '\'' +
                ", gzgCode='" + gzgCode + '\'' +
                '}';
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGzgCode() {
        return gzgCode;
    }

    public void setGzgCode(String gzgCode) {
        this.gzgCode = gzgCode;
    }
}