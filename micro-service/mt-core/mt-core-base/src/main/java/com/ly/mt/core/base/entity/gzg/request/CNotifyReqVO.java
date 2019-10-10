package com.ly.mt.core.base.entity.gzg.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/** @deprecated */
@ApiModel
public class CNotifyReqVO {
    @ApiModelProperty
    private long gzgOrderId;
    @ApiModelProperty
    private long gzgOrderItemId;
    @ApiModelProperty
    private int status;
    @ApiModelProperty
    private long hotelId;
    @ApiModelProperty
    private long gzgId;
    @ApiModelProperty
    private int cabinetNo;

}
