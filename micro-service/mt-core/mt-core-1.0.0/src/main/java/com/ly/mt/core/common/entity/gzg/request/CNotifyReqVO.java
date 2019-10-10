package com.ly.mt.core.common.entity.gzg.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
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
