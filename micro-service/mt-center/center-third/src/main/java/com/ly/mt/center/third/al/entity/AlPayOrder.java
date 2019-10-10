package com.ly.mt.center.third.al.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("阿里支付订单信息")
public class AlPayOrder {
    @ApiModelProperty(value = "订单号", required = true)
    private String out_trade_no;
    @ApiModelProperty(value = "订单金额", required = true)
    private String total_amount;
    @ApiModelProperty(value = "支付成功跳转地址", required = true)
    private String return_url;
    @ApiModelProperty(value = "商品名称", required = true)
    private String spu_name;
    @ApiModelProperty(value = "公用回传参数")
    private String passback_params;

    public String getPassback_params() {
        return passback_params;
    }

    public void setPassback_params(String passback_params) {
        this.passback_params = passback_params;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getSpu_name() {
        return spu_name;
    }

    public void setSpu_name(String spu_name) {
        this.spu_name = spu_name;
    }
}