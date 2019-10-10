package com.ly.mt.core.common.entity.trade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 484876123@qq.com
 */
@ApiModel( value = "更新退货单状态" ,description = "更新退货单状态")
public class UpdateRefundStatusDto {
    @ApiModelProperty(value = "退货单Id" ,name="id" ,required=true)
    private String id;

    @ApiModelProperty(value = "状态" ,name="refundStatus" ,required=true)
    private String refundStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }
}
