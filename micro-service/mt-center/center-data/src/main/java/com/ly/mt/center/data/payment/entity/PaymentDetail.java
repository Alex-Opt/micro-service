package com.ly.mt.center.data.payment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PaymentDetail {
    @ApiModelProperty(value = "id", required = true)
    private String id;
    @ApiModelProperty("订单号")
    private String order_no;
    @ApiModelProperty("收款方，如果支付方向为 1，则为平台，用-1标记。 如果是2，则为用户的Id")
    private String payee;
    @ApiModelProperty("付款方，如果支付方向为 1，则为用户的Id， 如果为 2，则为平台，用-1标记")
    private String payer;
    @ApiModelProperty("金额")
    private String money;
    @ApiModelProperty("交易类型")
    private String trade_type;
    @ApiModelProperty("交易状态")
    private String trade_status;
    @ApiModelProperty("支付方式")
    private String payment_type;
    @ApiModelProperty("支付单号")
    private String payment_no;
    @ApiModelProperty(value = "创建时间", required = true)
    private String create_time;
    @ApiModelProperty("备注，支付/退款失败的说明")
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getPayment_no() {
        return payment_no;
    }

    public void setPayment_no(String payment_no) {
        this.payment_no = payment_no;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}