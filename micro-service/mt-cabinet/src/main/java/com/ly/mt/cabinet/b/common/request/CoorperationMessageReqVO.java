package com.ly.mt.cabinet.b.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CoorperationMessageReqVO {
    @ApiModelProperty(value = "店铺id",name = "cabinet_store_id",example = "911837283788989")
    private String cabinet_store_id;

    public String getCabinet_store_id() {
        return cabinet_store_id;
    }

    public void setCabinet_store_id(String cabinet_store_id) {
        this.cabinet_store_id = cabinet_store_id;
    }
}
