package com.ly.mt.core.common.entity.trade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 484876123@qq.com
 */
@ApiModel( value = "更新退货单信息" ,description = "更新退货单信息")
public class UpdateRefundInfoDto {
    @ApiModelProperty(value = "退货单id" ,name="id" ,required=true)
    private String id;

    @ApiModelProperty(value = "退货地址" ,name="退货地址" ,required=true)
    private String retundAddress;

    @ApiModelProperty(value = "收货人名字" ,name="收货人名字" ,required=true)
    private String consignee;

    @ApiModelProperty(value = "收货人电话" ,name="收货人电话" ,required=true)
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRetundAddress() {
        return retundAddress;
    }

    public void setRetundAddress(String retundAddress) {
        this.retundAddress = retundAddress;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
