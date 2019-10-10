package com.ly.mt.core.common.entity.hd.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("查询兑换码请求对象")
public class QueryCodeRequestBody implements Serializable {

    @ApiModelProperty("openId")
    private String openId;

    @ApiModelProperty("activityId")
    private Long activityId;


}
