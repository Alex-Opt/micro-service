package com.ly.mt.open.wechat.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 微信用户 + openId  vo
 * @author pjt
 */
@ApiModel("微信用户信息")
public class WechatUserVo implements Serializable {

    @ApiModelProperty("openid")
    private String openId;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("性别")
    private String sexDesc;

    @ApiModelProperty("头像")
    private String headImgUrl;


    public WechatUserVo() {
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

    @Override
    public String toString() {
        return "WechatUserVo{" +
                "openId='" + openId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sexDesc='" + sexDesc + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                '}';
    }
}
