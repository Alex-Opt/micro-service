package com.ly.mt.core.common.entity.hd.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 买家订单查询请求体
 */
@ApiModel("买家订单查询请求体")
public class BuyerOrderRequestBody {

    @ApiModelProperty("门店活动信息主键id")
    private Long hdShopAttDetailId;

    @ApiModelProperty(value = "活动用户id")
    private Long attUserId;

    public Long getHdShopAttDetailId() {
        return hdShopAttDetailId;
    }

    public void setHdShopAttDetailId(Long hdShopAttDetailId) {
        this.hdShopAttDetailId = hdShopAttDetailId;
    }

    public Long getAttUserId() {
        return attUserId;
    }

    public void setAttUserId(Long attUserId) {
        this.attUserId = attUserId;
    }
}
