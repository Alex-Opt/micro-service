package com.ly.mt.core.common.entity.hd.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 添加门店请求对象
 */
@ApiModel("门店请求对象")
@Data
public class HdShopInfoRequestBody implements Serializable {

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("门店名称")
    private String shopName;

    @ApiModelProperty("门店地址")
    private String shopAddress;

    @ApiModelProperty("代理商id")
    private Long operatorId;


}
