package com.ly.mt.core.base.entity.gzg.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/** @deprecated */
@ApiModel
public class WyReplenishReqVO {
    @ApiModelProperty
    private long gzgCode;
    @ApiModelProperty
    private int cabinetNo;
    @ApiModelProperty
    private long replenishOrderId;
}
