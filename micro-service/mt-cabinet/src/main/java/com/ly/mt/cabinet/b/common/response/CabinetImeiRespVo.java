package com.ly.mt.cabinet.b.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("货柜号")
public class CabinetImeiRespVo {


    @ApiModelProperty("货柜号")
    private String imei;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
