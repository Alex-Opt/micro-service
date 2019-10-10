package com.ly.mt.core.common.entity.hd.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description
 * 基础微信助力请求类，后面有需要的请求体继承此类
 * @author panjingtian
 * @date 2019/8/14 8:22 PM
 */
@Data
@ApiModel("基础微信助力请求体")
public class BaseWechatHelpReauesyBody {


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("微信表主键id")
    private Long wechatId;


    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("活动id")
    private Long activityId;


}
