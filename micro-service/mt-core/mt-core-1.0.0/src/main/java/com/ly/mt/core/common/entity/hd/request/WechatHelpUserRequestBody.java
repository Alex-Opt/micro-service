package com.ly.mt.core.common.entity.hd.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("微信助力用户请求体")
public class WechatHelpUserRequestBody implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("活动id")
    private Long activityId;

    @ApiModelProperty("微信用户对公众号唯一id")
    private String openId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("微信用户主键id")
    private Long wechatId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("wechatMasterId")
    private Long wechatMasterId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("taskId")
    private Long taskId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("shareUrl")
    private String shareUrl;


}
