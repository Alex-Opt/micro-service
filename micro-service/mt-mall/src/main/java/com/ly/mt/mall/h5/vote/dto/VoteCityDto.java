package com.ly.mt.mall.h5.vote.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 投票入参对象
 */
@ApiModel("投票入参对象")
public class VoteCityDto {

    @ApiModelProperty(value = "用户id", required = true)
    private String userId;
    @ApiModelProperty(value = "区域id", required = true)
    private String areaId;

    public VoteCityDto() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
}
