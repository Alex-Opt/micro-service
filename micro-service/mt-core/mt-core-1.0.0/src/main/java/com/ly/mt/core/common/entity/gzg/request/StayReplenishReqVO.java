package com.ly.mt.core.common.entity.gzg.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class StayReplenishReqVO {
    @ApiModelProperty
    private long gzgCode;
    @ApiModelProperty
    private int cabinetNo;
}
