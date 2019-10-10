package com.ly.mt.core.common.entity.gzg.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class WyReplenishReqVO {
    @ApiModelProperty
    private long gzgCode;
    @ApiModelProperty
    private int cabinetNo;
    @ApiModelProperty
    private long replenishOrderId;
}
