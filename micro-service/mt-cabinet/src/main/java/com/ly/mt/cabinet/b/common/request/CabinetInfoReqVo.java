package com.ly.mt.cabinet.b.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 仓库管理员在绑定柜子时返回给前端页面用于
 * 展示目前货柜类型和配货方案
 */
@ApiModel
public class CabinetInfoReqVo implements Serializable {


    @ApiModelProperty("货柜类型")
    private String type;

    @ApiModelProperty("配货方案")
    private List<String> list;


}