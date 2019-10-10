package com.ly.mt.center.third.wx.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("微信支付入参信息")
public class WxPayOrder {
    @ApiModelProperty(value = "appId", required = true)
    private String appid;
    @ApiModelProperty(value = "交易类型", required = true)
    private String trade_type;
    @ApiModelProperty(value = "订单号", required = true)
    private String out_trade_no;
    @ApiModelProperty(value = "订单金额", required = true)
    private String total_fee;
    @ApiModelProperty(value = "商品描述", required = true)
    private String body;
    @ApiModelProperty("场景信息")
    private String scene_info;
    @ApiModelProperty(value = "trade_type=JSAPI时（即JSAPI支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识", required = true)
    private String openId;
    @ApiModelProperty(value = "用户ip", required = true)
    private String ip;
    @ApiModelProperty("公用回传参数,目前咱们支付系统用来支付回调时，区分支付产生来源是到家C，还是活动，格子柜等，好针对性发送mq")
    private String attach;

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getScene_info() {
        return scene_info;
    }

    public void setScene_info(String scene_info) {
        this.scene_info = scene_info;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}