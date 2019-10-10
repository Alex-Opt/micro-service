package com.ly.mt.gzg.b.web.config.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class UserReqVO {
    @ApiModelProperty(value = "电话号码",name = "phoneNo",example = "13534343435")
    private String phoneNo;
    @ApiModelProperty(value = "动态码",name = "dynamicCode",example = "1343")
    private String dynamicCode;
    @ApiModelProperty(value = "客户端ID",name = "clientId",example = "cbc1f25363449ad1a4a2c3cc0ab6e1b1")
    private String clientId;
}
