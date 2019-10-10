package com.ly.mt.center.data.cabinet.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CoorperationMessageReqVO {
    @ApiModelProperty
    private String cabinet_store_id;

    public String getCabinet_store_id() {
        return cabinet_store_id;
    }

    public void setCabinet_store_id(String cabinet_store_id) {
        this.cabinet_store_id = cabinet_store_id;
    }
}
