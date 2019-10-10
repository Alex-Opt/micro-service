package com.ly.mt.activity.wechart.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author taoye
 */
@ApiModel(value = "code=0时返回")
public class WeChartOpenIdVo {
    @ApiModelProperty(value = "openid")
    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}