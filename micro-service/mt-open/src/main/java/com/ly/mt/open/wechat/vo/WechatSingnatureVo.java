package com.ly.mt.open.wechat.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * wechat Jssdk返回校验VO
 */
@ApiModel("jssdk,singnature-vo")
public class WechatSingnatureVo implements Serializable {

    @ApiModelProperty("appid")
    private String appId;

    @ApiModelProperty("nonceStr")
    private String nonceStr;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("timestamp")
    private long timestamp;

    @ApiModelProperty("url")
    private String url;

    @ApiModelProperty("signature")
    private String signature;

    public WechatSingnatureVo() {
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
