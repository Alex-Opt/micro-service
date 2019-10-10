package com.ly.mt.core.common.entity.hd.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("代理商请求对象")
@Data
public class HdOperatorRequestBody implements Serializable {

    @ApiModelProperty("代理商名称")
    private String operatorName;
}
