package com.ly.mt.core.common.entity.hd.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("助力请求对象")
public class DoHelpRequestBody implements Serializable {


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("活动id")
    private Long activityId;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("被助力人id")
    private Long masterId;


    @ApiModelProperty("微信用户表主键id")
    private Long wechatId;

    @ApiModelProperty("助力人的openid")
    private String openId;

}
