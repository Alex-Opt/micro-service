package com.ly.mt.cabinet.b.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询bd指定店铺所有订单详情入参")
public class DataStarusticsOrdersRequestBody extends DataStarusticsRequestBody {

    @ApiModelProperty("店铺id")
    private String cabinet_store_id;

    public String getCabinet_store_id() {
        return cabinet_store_id;
    }

    public void setCabinet_store_id(String cabinet_store_id) {
        this.cabinet_store_id = cabinet_store_id;
    }


}
