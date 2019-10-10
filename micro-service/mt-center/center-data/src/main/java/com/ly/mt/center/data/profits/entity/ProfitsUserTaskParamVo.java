package com.ly.mt.center.data.profits.entity;

import io.swagger.annotations.ApiModelProperty;

public class ProfitsUserTaskParamVo {

    @ApiModelProperty("用户id")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
