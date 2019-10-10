package com.ly.mt.core.common.entity.hd.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *  微信用户请求题
 */
@ApiModel("微信用户信息")
public class WechatUserRequestBody {

    @ApiModelProperty("openid")
    private String openId;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("性别")
    private String sexDesc;

    @ApiModelProperty("头像")
    private String headImgUrl;

    public WechatUserRequestBody() {
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSexDesc() {
        return sexDesc;
    }

    public void setSexDesc(String sexDesc) {
        this.sexDesc = sexDesc;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }
}
