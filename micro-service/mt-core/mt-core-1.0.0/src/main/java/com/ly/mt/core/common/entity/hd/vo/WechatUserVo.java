package com.ly.mt.core.common.entity.hd.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 微信用户vo
 */
@ApiModel("微信用户信息展示类")
@Data
public class WechatUserVo implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("openid")
    private String openId;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("性别")
    private String sexDesc;

    @ApiModelProperty("头像")
    private String headImgUrl;

}
