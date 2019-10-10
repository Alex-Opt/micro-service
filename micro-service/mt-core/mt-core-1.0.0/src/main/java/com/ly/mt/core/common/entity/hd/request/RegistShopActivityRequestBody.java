package com.ly.mt.core.common.entity.hd.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 注册门店请求体
 */
@ApiModel("门店注册请求体")
public class RegistShopActivityRequestBody {


    @ApiModelProperty("门店注册信息")
    private HdShopAttDetailModelRequestBody hdShopAttDetailModel;

    @ApiModelProperty("验证码")
    private String dynamicCode;

    public HdShopAttDetailModelRequestBody getHdShopAttDetailModel() {
        return hdShopAttDetailModel;
    }

    public void setHdShopAttDetailModel(HdShopAttDetailModelRequestBody hdShopAttDetailModel) {
        this.hdShopAttDetailModel = hdShopAttDetailModel;
    }

    public String getDynamicCode() {
        return dynamicCode;
    }

    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
    }
}
