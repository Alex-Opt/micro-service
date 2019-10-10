package com.ly.mt.center.data.cabinet.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CreateBussinessCoopReqVO {
    @ApiModelProperty
    private CabinetStoreReqVo cabinetStoreReqVo;
    @ApiModelProperty
    private CabinetContractReqVo cabinetContractReqVo;
    @ApiModelProperty
    private CabinetStorePropertyReqVo cabinetStorePropertyReqVo;
    @ApiModelProperty
    private CabinetBussinessCoorperationReqVO cabinetBussinessCoorperationReqVO;

}
