package com.ly.mt.cabinet.c.wechat.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class WeChatPayReqVO {

    @ApiModelProperty(value = "订单id", name = "gzgOrderId", example = "592091927407890432")
    private String gzgOrderId;

    @ApiModelProperty(value = "前端获取code值（限微信浏览器支付）", name = "code", example = "0712CsHF1x6g770theHF1NsyHF12CsHQ")
    private String code;


    public String getGzgOrderId() {
        return gzgOrderId;
    }

    public void setGzgOrderId(String gzgOrderId) {
        this.gzgOrderId = gzgOrderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
