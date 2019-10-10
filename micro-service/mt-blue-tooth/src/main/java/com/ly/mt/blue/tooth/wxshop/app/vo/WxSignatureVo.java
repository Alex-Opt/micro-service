package com.ly.mt.blue.tooth.wxshop.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @program: mt-blue-tooth
* @description: 微信小程序店铺信息
* @author: wanghongliang
* @create: 2019/8/2 10:55
**/
@ApiModel(value="微信签名对象")
public class WxSignatureVo {
    @ApiModelProperty(value="微信公众号appID",required=true)
    private String appId;
    @ApiModelProperty(value ="时间戳",required = false)
    private String timestamp;
    @ApiModelProperty(value="随机数",required = true)
    private String nonceStr;
    @ApiModelProperty(value="签名",required=true)
    private String signature;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}