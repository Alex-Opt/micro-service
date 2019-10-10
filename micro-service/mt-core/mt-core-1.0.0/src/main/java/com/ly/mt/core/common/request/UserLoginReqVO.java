package com.ly.mt.core.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class UserLoginReqVO {
    @ApiModelProperty(value = "电话号码",name = "phoneNo",example = "13534343435")
    private String phoneNo;
    @ApiModelProperty(value = "动态码",name = "dynamicCode",example = "1343")
    private String dynamicCode;
}
