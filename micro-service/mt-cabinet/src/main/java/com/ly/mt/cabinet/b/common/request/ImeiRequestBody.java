package com.ly.mt.cabinet.b.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * imei请求
 */
@ApiModel("imei请求体")
public class ImeiRequestBody {

    @ApiModelProperty
    private String imei;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
